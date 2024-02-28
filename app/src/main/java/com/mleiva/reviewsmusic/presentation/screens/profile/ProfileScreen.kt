package com.mleiva.reviewsmusic.presentation.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.screens.profile.components.ProfileContent
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile
 * Creted by: Marcelo Leiva on 27-02-2024 at 16:53
 ***/
@Composable
fun ProfileScreen(navHostController: NavHostController){

    Scaffold(
        topBar = {},
        content = {
            ProfileContent(it, navHostController)
        },
        bottomBar = {}
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileSreen() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ProfileScreen(rememberNavController())
        }
    }
}