package com.mleiva.reviewsmusic.domain.use_cases.posts

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.posts
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:49
 ***/
data class PostsUseCases(
    val create: CreatePosts,
    val getPostsByIdUser: GetPostsByIdUser,
    val deletePost: DeletePost,
    val getPosts: GetPosts,
    val likePost: LikePost,
    val deleteLikePost: DeleteLikePost,
    val updatePost: UpdatePost
)
