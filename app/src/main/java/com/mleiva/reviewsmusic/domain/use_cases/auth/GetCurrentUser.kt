package com.mleiva.reviewsmusic.domain.use_cases.auth

import com.mleiva.reviewsmusic.domain.repository.AuthRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.auth
 * Creted by: Marcelo Leiva on 26-02-2024 at 21:01
 ***/
class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

 operator fun invoke() = repository.currentUser

}