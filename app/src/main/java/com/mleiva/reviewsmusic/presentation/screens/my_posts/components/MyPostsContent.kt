package com.mleiva.reviewsmusic.presentation.screens.my_posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.domain.model.Post

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.my_posts.components
 * Creted by: Marcelo Leiva on 05-03-2024 at 20:14
 ***/
@Composable
fun MyPostsContent(navHostController: NavHostController, posts: List<Post>){


    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 60.dp),
        //content =
    ){
        items(
            items = posts
        ) { post ->
            MyPostsCard(navHostController,post)
        }
    }

}