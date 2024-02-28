package com.mleiva.reviewsmusic.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.components
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:56
 ***/
@Composable
fun ProgressBar(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}