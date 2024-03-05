package com.mleiva.reviewsmusic.presentation.screens.my_posts.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.presentation.components.ProgressBar
import com.mleiva.reviewsmusic.presentation.screens.my_posts.MyPostsViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.my_posts.components
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:34
 ***/
@Composable
fun GetPostsByIdUser(navHostController: NavHostController, viewModel: MyPostsViewModel = hiltViewModel()){

    when(val responseNewPost = viewModel.postResponse){

        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            MyPostsContent(navHostController, responseNewPost.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, responseNewPost.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetPostsByIdUserPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            GetPostsByIdUser(rememberNavController())
        }
    }
}