package com.mleiva.reviewsmusic.domain.repository

import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import java.io.File

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.repository
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:46
 ***/
interface PostsRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>

}