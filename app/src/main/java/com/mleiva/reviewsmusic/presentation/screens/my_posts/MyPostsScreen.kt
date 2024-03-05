package com.mleiva.reviewsmusic.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.presentation.navigation.DetailsScreen
import com.mleiva.reviewsmusic.presentation.screens.my_posts.components.GetPostsByIdUser
import com.mleiva.reviewsmusic.presentation.screens.profile.ProfileScreen
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.my_posts
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:26
 ***/
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navHostController: NavHostController){

    Scaffold(
      content = {
          GetPostsByIdUser(navHostController = navHostController)
      },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { navHostController.navigate(DetailsScreen.NewPost.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMyPostsScreen() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MyPostsScreen(rememberNavController())
        }
    }
}