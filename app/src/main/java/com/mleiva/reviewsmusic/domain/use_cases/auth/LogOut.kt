package com.mleiva.reviewsmusic.domain.use_cases.auth

import com.mleiva.reviewsmusic.domain.repository.AuthRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.auth
 * Creted by: Marcelo Leiva on 27-02-2024 at 17:21
 ***/
class LogOut @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.logout()

}