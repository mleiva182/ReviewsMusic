package com.mleiva.reviewsmusic.domain.use_cases.users

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.users
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:27
 ***/
data class UsersUseCases(
    val create: Create,
    val getUserById: GetUserById,
    val saveImage: SaveImage,
    val update: Update
)
