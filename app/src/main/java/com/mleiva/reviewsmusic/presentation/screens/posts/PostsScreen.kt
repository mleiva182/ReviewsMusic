package com.mleiva.reviewsmusic.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.presentation.screens.posts.components.DeleteLikePost
import com.mleiva.reviewsmusic.presentation.screens.posts.components.GetPosts
import com.mleiva.reviewsmusic.presentation.screens.posts.components.LikePost

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.posts
 * Creted by: Marcelo Leiva on 06-03-2024 at 12:11
 ***/
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsScreen(navHostController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){

    Scaffold(
        content = {
            GetPosts(navHostController = navHostController)
        }
    )

    LikePost()
    DeleteLikePost()

}