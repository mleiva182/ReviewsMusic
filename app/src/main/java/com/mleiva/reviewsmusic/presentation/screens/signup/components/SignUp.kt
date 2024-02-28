package com.mleiva.reviewsmusic.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.navigation.Graph
import com.mleiva.reviewsmusic.presentation.screens.signup.SignUpViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.signup
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:06
 ***/
@Composable
fun SignUp(navHostController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {

    when(val singInResponse = viewModel.singInResponse){

        Response.Loading ->{
            ProgressBar()
        }

        is Response.Success  -> {
            LaunchedEffect(Unit){
                viewModel.createUser()
                navHostController.popBackStack(Graph.AUTHENTICATION, true)
                navHostController.navigate(route = Graph.HOME)
            }
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, singInResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}