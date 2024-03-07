package com.mleiva.reviewsmusic.domain.repository

import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.repository
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:46
 ***/
interface PostsRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
    suspend fun delete(idPost: String): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
    suspend fun like(idPost: String, idUser: String): Response<Boolean>
    suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean>
}