package com.mleiva.reviewsmusic.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.mleiva.reviewsmusic.core.Constants.USERS
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.data.repository
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:31
 ***/
class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference
): UsersRepository {

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow{
        val snapshotListener = usersRef.document(id).addSnapshotListener{ snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

}