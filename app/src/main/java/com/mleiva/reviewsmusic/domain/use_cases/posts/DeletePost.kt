package com.mleiva.reviewsmusic.domain.use_cases.posts

import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.posts
 * Creted by: Marcelo Leiva on 05-03-2024 at 20:03
 ***/
class DeletePost @Inject constructor(
    private val repository: PostsRepository
){

    suspend operator fun invoke(idPost: String) = repository.delete(idPost)

}