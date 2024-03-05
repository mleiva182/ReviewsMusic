package com.mleiva.reviewsmusic.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.ProfileEditViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile_edit.components
 * Creted by: Marcelo Leiva on 28-02-2024 at 12:05
 ***/
@Composable
fun SaveImage(viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val saveImageResponse = viewModel.saveImageResponse){

        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success ->{
            Toast.makeText(LocalContext.current, "Datos actualizados", Toast.LENGTH_LONG).show()
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, saveImageResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}