package com.mleiva.reviewsmusic.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mleiva.reviewsmusic.presentation.screens.profile.ProfileScreen

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.navigation
 * Creted by: Marcelo Leiva on 27-02-2024 at 16:47
 ***/
@Composable
fun HomeBottomBarNavGraph(paddingValues: PaddingValues, navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Posts.route
    ){

        composable(route = HomeBottomBarScreen.Posts.route){
            //PostScreen(navHostController)
        }

        composable(route = HomeBottomBarScreen.MyPosts.route){
            //MyPostsScreen(navHostController)
        }

        composable(route = HomeBottomBarScreen.Profile.route){
            ProfileScreen(navHostController)
        }

        //detailsNavGrapgh(navHostController)
    }

}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Posts: HomeBottomBarScreen(
        route = "posts",
        title = "Posts",
        icon = Icons.Default.List
    )

    object MyPosts: HomeBottomBarScreen(
        route = "my_list",
        title = "Mis Posts",
        icon = Icons.Default.List
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}