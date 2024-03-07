package com.mleiva.reviewsmusic.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.posts.PostsViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.posts.components
 * Creted by: Marcelo Leiva on 06-03-2024 at 12:14
 ***/
@Composable
fun GetPosts(navHostController: NavHostController,viewModel: PostsViewModel = hiltViewModel()){

    when(val responseNewPost = viewModel.postsResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            PostsContent(navHostController, responseNewPost.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, responseNewPost.exception?.message?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}