package com.mleiva.reviewsmusic.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mleiva.reviewsmusic.presentation.screens.login.LoginScreen
import com.mleiva.reviewsmusic.presentation.screens.signup.SignUpScreen

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.navigation
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:13
 ***/
fun NavGraphBuilder.authNavGraph(navHostController: NavHostController){

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navHostController)
        }
        composable(route = AuthScreen.Signup.route){
            SignUpScreen(navHostController)
        }
    }

}

sealed class AuthScreen(val route: String){

    object Login: AuthScreen("login")
    object Signup: AuthScreen("signup")

}