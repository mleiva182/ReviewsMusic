package com.mleiva.reviewsmusic.presentation.screens.update_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.presentation.components.DefaultButton
import com.mleiva.reviewsmusic.presentation.components.DefaultTopBar
import com.mleiva.reviewsmusic.presentation.screens.update_post.components.UpdatePost
import com.mleiva.reviewsmusic.presentation.screens.update_post.components.UpdatePostContent

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.update_post
 * Creted by: Marcelo Leiva on 12-03-2024 at 15:37
 ***/
@Composable
fun UpdatePostScreen(navHostController: NavHostController,
                     viewModel: UpdatePostViewModel = hiltViewModel(),
                     post:String){

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar post",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            UpdatePostContent(it)
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Actualizar",
                onClick = { viewModel.onUpdatePost() })
        }
    )

    UpdatePost()

}