package com.mleiva.reviewsmusic.domain.use_cases.auth

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.auth
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:19
 ***/
data class AuthUseCase(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val signUp: SignUp,
    val logout: LogOut
)
