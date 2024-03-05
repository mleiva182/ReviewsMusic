package com.mleiva.reviewsmusic.domain.use_cases.posts

import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.posts
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:49
 ***/
class CreatePosts @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(post: Post, file: File) = repository.create(post, file)
}