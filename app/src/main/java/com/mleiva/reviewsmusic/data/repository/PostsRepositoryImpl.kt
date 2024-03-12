package com.mleiva.reviewsmusic.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.mleiva.reviewsmusic.core.Constants.POSTS
import com.mleiva.reviewsmusic.core.Constants.USERS
import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.data.repository
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:52
 ***/
class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
): PostsRepository{
    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {
            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATA
            post.image = url.toString()
            postsRef.add(post).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow{

        val snapShotListener = postsRef.addSnapshotListener{ snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if (snapshot != null){

                    val posts = snapshot.toObjects(Post::class.java)

                    snapshot.documents.forEachIndexed{ index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }

                    val idUserArray = ArrayList<String>()

                    posts.forEach {
                        idUserArray.add(it.idUser)
                    }
                    val idUserList = idUserArray.toSet().toList()

                    idUserList.map {id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                            posts.forEach { post ->
                                if (post.idUser == id){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Success(posts)
                }else{
                    Response.Failure(e)
                }
                trySend(postResponse)
            }

        }
        awaitClose{
            snapShotListener.remove()
        }

    }

    override suspend fun delete(idPost: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).delete().await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {

        val snapShotListener = postsRef.addSnapshotListener{ snapshot, e ->
            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if (snapshot != null){

                    val posts = snapshot.toObjects(Post::class.java)

                    snapshot.documents.forEachIndexed{ index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }

                    val idUserArray = ArrayList<String>()

                    posts.forEach {
                        idUserArray.add(it.idUser)
                    }
                    val idUserList = idUserArray.toSet().toList()

                    idUserList.map {id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                            posts.forEach { post ->
                                if (post.idUser == id){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Success(posts)
                }else{
                    Response.Failure(e)
                }
                trySend(postResponse)
            }

        }
        awaitClose{
            snapShotListener.remove()
        }

    }

    override suspend fun like(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update("likes", FieldValue.arrayUnion(idUser)).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update("likes", FieldValue.arrayRemove(idUser)).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try {
            //IMAGE
            if(file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storagePostsRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                post.image = url.toString()
            }

            val map: MutableMap<String, Any> = HashMap()
            map["name"] = post.name
            map["description"] = post.description
            map["category"] = post.category
            map["image"] = post.image
            //DATA
            postsRef.document(post.id).update(map).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}