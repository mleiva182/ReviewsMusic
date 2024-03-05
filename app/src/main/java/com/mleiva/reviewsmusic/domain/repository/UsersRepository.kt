package com.mleiva.reviewsmusic.domain.repository

import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.repository
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:29
 ***/
interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
    suspend fun saveImage(file: File): Response<String>
    suspend fun update(user: User, image: String): Response<Boolean>

}