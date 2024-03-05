package com.mleiva.reviewsmusic.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.components
 * Creted by: Marcelo Leiva on 28-02-2024 at 12:03
 ***/
@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit,
){
    if(status.value){
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            onDismissRequest = { status.value = false },
            backgroundColor = Color.White,
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            pickImage()
                        }
                    ) {
                        Text(text = "Galeria")
                    }
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            takePhoto()
                        }
                    ) {
                        Text(text = "Camara")
                    }
                }
            },
            title = {
                Text(
                    text = "Selecciona una opción",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        )
    }
}