package com.mleiva.reviewsmusic.presentation.screens.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.screens.login.components.Login
import com.mleiva.reviewsmusic.presentation.screens.login.components.LoginBottomBar
import com.mleiva.reviewsmusic.presentation.screens.login.components.LoginContent
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.login
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:21
 ***/
@Composable
fun LoginScreen(navHostController: NavHostController){


    Scaffold(
        topBar = {},
        content = {
            LoginContent(it, navHostController)
        },
        bottomBar = {
            LoginBottomBar(navHostController)
        }
    )
    //Para manejar el estadod de peticion Login
    Login(navHostController)

}

@Composable
fun BodyContent(paddingValues: PaddingValues){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}