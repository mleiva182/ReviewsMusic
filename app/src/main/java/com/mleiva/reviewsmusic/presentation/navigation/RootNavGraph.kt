package com.mleiva.reviewsmusic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mleiva.reviewsmusic.presentation.screens.home.HomeScreen

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.navigation
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:06
 ***/
@Composable
fun RootNavGraph(navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navHostController)

        composable(route = Graph.HOME){
            HomeScreen()
        }
    }

}