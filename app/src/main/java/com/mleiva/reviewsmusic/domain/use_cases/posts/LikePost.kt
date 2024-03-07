package com.mleiva.reviewsmusic.domain.use_cases.posts

import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.posts
 * Creted by: Marcelo Leiva on 06-03-2024 at 12:27
 ***/
class LikePost @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke(idPost: String, idUser: String) = repository.like(idPost, idUser)

}