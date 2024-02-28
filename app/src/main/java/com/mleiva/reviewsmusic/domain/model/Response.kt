package com.mleiva.reviewsmusic.domain.model

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.model
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:13
 ***/
sealed class Response<out T> {

    object Loading: Response<Nothing>()
    data class Success<out T>(val data: T): Response<T>()
    data class Failure<out T>(val exception: Exception?): Response<T>()

}