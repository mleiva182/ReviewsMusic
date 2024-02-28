package com.mleiva.reviewsmusic.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.navigation.Graph
import com.mleiva.reviewsmusic.presentation.screens.login.LoginViewModel

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.login.components
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:40
 ***/
@Composable
fun Login(navHostController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){


    when(val loginResponse = viewModel.loginResponse){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navHostController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION) {inclusive= true}
                }
            }
            Toast.makeText(LocalContext.current, "Usuario Logeado", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResponse.exception?.message?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}