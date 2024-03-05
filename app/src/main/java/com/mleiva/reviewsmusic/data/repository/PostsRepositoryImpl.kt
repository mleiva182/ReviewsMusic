package com.mleiva.reviewsmusic.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.mleiva.reviewsmusic.core.Constants.POSTS
import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.repository.PostsRepository
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
}