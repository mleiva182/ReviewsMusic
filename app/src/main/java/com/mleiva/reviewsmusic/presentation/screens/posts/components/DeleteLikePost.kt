package com.mleiva.reviewsmusic.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.posts.PostsViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.posts.components
 * Creted by: Marcelo Leiva on 07-03-2024 at 12:07
 ***/
@Composable
fun DeleteLikePost(viewModel: PostsViewModel = hiltViewModel()){

    when(val deleteLikeResponse = viewModel.deleteLikeResponse){

        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {}
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, deleteLikeResponse.exception?.message?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}

    }

}