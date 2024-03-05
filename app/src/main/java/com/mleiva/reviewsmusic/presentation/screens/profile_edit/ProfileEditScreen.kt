package com.mleiva.reviewsmusic.presentation.screens.profile_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.presentation.components.DefaultTopBar
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.components.ProfileEditContent
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.components.SaveImage
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.components.Update

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile_edit
 * Creted by: Marcelo Leiva on 28-02-2024 at 11:52
 ***/
@Composable
fun ProfileEditScreen(navHostController: NavHostController, user: String){

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Usuario",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            ProfileEditContent(paddingValues = it, navHostController)
        },
        bottomBar = {}
    )
    SaveImage()
    Update()

}