package com.mleiva.reviewsmusic.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mleiva.reviewsmusic.presentation.screens.detail_post.DetailPostScreen
import com.mleiva.reviewsmusic.presentation.screens.new_post.NewPostScreen
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.ProfileEditScreen

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.navigation
 * Creted by: Marcelo Leiva on 02-03-2024 at 17:14
 ***/
fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController){

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){
        composable( route = DetailsScreen.NewPost.route){
            NewPostScreen(navHostController)
        }
        composable(
            route = DetailsScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            }
            )
        ){
            it.arguments?.getString("user")?.let {
                ProfileEditScreen(navHostController, user = it)
            }
        }

        composable(
            route = DetailsScreen.DetailPost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            }
            )
        ){
            it.arguments?.getString("post")?.let {
                DetailPostScreen(navHostController, post = it)
            }
        }

        composable(
            route = DetailsScreen.UpdatePost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            }
            )
        ){
            it.arguments?.getString("post")?.let {
                //UpdatePostScreen(navHostController, post = it)
            }
        }
    }

}

sealed class DetailsScreen(val route: String){

    object NewPost: DetailsScreen("posts/new")

    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/$user"
    }
    object DetailPost: DetailsScreen("posts/detail/{post}"){
        fun passPost(post: String) = "posts/detail/$post"
    }
    object UpdatePost: DetailsScreen("posts/update/{post}"){
        fun passPost(post: String) = "posts/update/$post"
    }

}