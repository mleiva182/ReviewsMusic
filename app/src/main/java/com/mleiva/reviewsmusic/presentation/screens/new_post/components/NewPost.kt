package com.mleiva.reviewsmusic.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.new_post.NewPostViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.new_post.components
 * Creted by: Marcelo Leiva on 05-03-2024 at 15:10
 ***/
@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()){

    when(val responseNewPost = viewModel.createPostResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            viewModel.clearForm()
            //Toast.makeText(LocalContext.current,  "La publicación se creo correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, responseNewPost.exception?.message?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}