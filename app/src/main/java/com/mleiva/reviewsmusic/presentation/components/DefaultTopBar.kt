package com.mleiva.reviewsmusic.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.components
 * Creted by: Marcelo Leiva on 26-02-2024 at 19:57
 ***/
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null // NULL SAFETY para null pointer exception
) {

    TopAppBar(
        title = {
            Text(
                title,
                fontSize = 19.sp
            )
        },
        backgroundColor = Brown500,
        navigationIcon = {
            if(upAvailable){
                IconButton(onClick = {
                    navHostController?.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }

        }
    )

}