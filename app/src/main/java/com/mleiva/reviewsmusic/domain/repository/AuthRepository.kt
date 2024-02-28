package com.mleiva.reviewsmusic.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.repository
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:22
 ***/
interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>

    suspend fun signUp(user: User): Response<FirebaseUser>
    fun logout()

}