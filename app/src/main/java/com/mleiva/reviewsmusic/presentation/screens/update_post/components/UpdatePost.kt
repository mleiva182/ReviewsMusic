package com.mleiva.reviewsmusic.presentation.screens.update_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.update_post.UpdatePostViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.update_post.components
 * Creted by: Marcelo Leiva on 12-03-2024 at 15:53
 ***/
@Composable
fun UpdatePost(viewModel: UpdatePostViewModel = hiltViewModel()){

    when(val responseNewPost = viewModel.updatePostResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current,  "La publicación se actualizo correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, responseNewPost.exception?.message?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}