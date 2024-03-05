package com.mleiva.reviewsmusic.presentation.screens.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.components.DefaultButton
import com.mleiva.reviewsmusic.presentation.components.DefaultTopBar
import com.mleiva.reviewsmusic.presentation.screens.my_posts.MyPostsScreen
import com.mleiva.reviewsmusic.presentation.screens.new_post.components.NewPost
import com.mleiva.reviewsmusic.presentation.screens.new_post.components.NewPostContent
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.new_post
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:40
 ***/
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navHostController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()){

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo post",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "PUBLICAR",
                onClick = { viewModel.onNewPost() })
        }
    )

    NewPost()

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewPostScreen() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewPostScreen(rememberNavController())
        }
    }
}