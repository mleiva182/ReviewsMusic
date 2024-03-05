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
 * Creted by: Marcelo Leiva on 28-02-2024 at 12:06
 ***/
@Composable
fun Update(viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val saveImageResponse = viewModel.updateResponse){

        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success ->{
            /*if(!viewModel.imageUri.isEmpty()){
                viewModel.onUpdate(saveImageResponse.data)
                Toast.makeText(LocalContext.current, "Datos actualizados", Toast.LENGTH_LONG).show()
            } else if (!viewModel.state.userName.equals(viewModel.user.userName)){
                viewModel.onUpdate(saveImageResponse.data)
                Toast.makeText(LocalContext.current, "Datos actualizados", Toast.LENGTH_LONG).show()
            }*/
            if(!viewModel.imageUri.isEmpty()){
                //viewModel.onUpdate(viewModel.imageUri)
                Toast.makeText(LocalContext.current, "Datos actualizados", Toast.LENGTH_LONG).show()
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, saveImageResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}