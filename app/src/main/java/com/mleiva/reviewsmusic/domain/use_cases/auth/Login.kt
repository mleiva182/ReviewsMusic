package com.mleiva.reviewsmusic.domain.use_cases.auth

import com.mleiva.reviewsmusic.domain.repository.AuthRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.auth
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:39
 ***/
class Login @Inject constructor(private val repository: AuthRepository){

    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)

}