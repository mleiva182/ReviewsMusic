package com.mleiva.reviewsmusic.presentation.screens.detail_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.screens.detail_post.components.DetailPostContent
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.detail_post
 * Creted by: Marcelo Leiva on 11-03-2024 at 10:19
 ***/
@Composable
fun DetailPostScreen(navHostController: NavHostController, post: String){

    Scaffold(
        content = {
            DetailPostContent(paddingValues = it, navHostController)
        }
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailPostScreenPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DetailPostScreen(rememberNavController(),"")
        }
    }
}