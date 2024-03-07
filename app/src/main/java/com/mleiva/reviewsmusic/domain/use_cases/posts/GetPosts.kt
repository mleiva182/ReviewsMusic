package com.mleiva.reviewsmusic.domain.use_cases.posts

import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.posts
 * Creted by: Marcelo Leiva on 06-03-2024 at 12:25
 ***/
class GetPosts @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke() = repository.getPosts()

}