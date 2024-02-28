package com.mleiva.reviewsmusic.presentation.screens.signup

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.presentation.components.DefaultTopBar
import com.mleiva.reviewsmusic.presentation.screens.signup.components.SignUp
import com.mleiva.reviewsmusic.presentation.screens.signup.components.SignUpContent

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.signup
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:24
 ***/
@Composable
fun SignUpScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Usuario",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            SignUpContent(paddingValues = it, navHostController)
        },
        bottomBar = {}
    )
    SignUp(navHostController)
}