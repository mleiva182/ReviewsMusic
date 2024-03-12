package com.mleiva.reviewsmusic.presentation.screens.update_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.presentation.components.DefaultTextField
import com.mleiva.reviewsmusic.presentation.components.DialogCapturePicture
import com.mleiva.reviewsmusic.presentation.screens.update_post.UpdatePostViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.update_post.components
 * Creted by: Marcelo Leiva on 12-03-2024 at 15:52
 ***/
@Composable
fun UpdatePostContent(paddingValues: PaddingValues,
                      viewModel: UpdatePostViewModel = hiltViewModel()){

    viewModel.resultingActivityHandler.handle()
    val state = viewModel.state

    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )

    Box (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .background(Brown500)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (viewModel.imageUri != ""){
                        viewModel.onImageInput(viewModel.imageUri)
                        AsyncImage(
                            modifier = Modifier
                                .height(180.dp)
                                .width(100.dp)
                                .fillMaxWidth()
                                .clickable {
                                    dialogState.value = true
                                },
                            model = viewModel.state.image,
                            contentDescription = "Selected Image",
                            contentScale = ContentScale.Crop)

                    }else {
                        Image(
                            modifier = Modifier
                                .height(120.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "Añadir una Imagen"
                        )
                        Text(
                            text = "SELECCIONA UNA IMAGEN",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }


            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = state.name,
                onValueChange = {
                    viewModel.onNameInput(it)
                },
                label = "Nombre del Grupo",
                icon = Icons.Default.Face,
                errorMsg = "",
                validateField = {

                }
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = state.description,
                onValueChange = {
                    viewModel.onDescriptionInput(it)
                },
                label = "Nombre del disco",
                icon = Icons.Default.List,
                errorMsg = "",
                validateField = {

                }
            )

            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "CATEGORIA MUSICA",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            viewModel.radioOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = (option.category == state.category),
                            onClick = {
                                viewModel.onCategoryInput(option.category)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option.category == state.category),
                        onClick = {
                            viewModel.onCategoryInput(option.category)
                        }
                    )
                    Row {
                        Text(
                            modifier = Modifier
                                .width(110.dp)
                                .padding(12.dp),
                            text = option.category
                        )
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(8.dp),
                            painter = painterResource(id = option.image),
                            contentDescription = ""
                        )
                    }
                }

            }

        }
    }

}