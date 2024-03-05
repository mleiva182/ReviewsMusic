package com.mleiva.reviewsmusic.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.presentation.components.DefaultButton
import com.mleiva.reviewsmusic.presentation.components.DefaultTextField
import com.mleiva.reviewsmusic.presentation.components.DialogCapturePicture
import com.mleiva.reviewsmusic.presentation.screens.profile.ProfileViewModel
import com.mleiva.reviewsmusic.presentation.screens.profile_edit.ProfileEditViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500
import com.mleiva.reviewsmusic.presentation.ui.theme.DarkGray700

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile_edit.components
 * Creted by: Marcelo Leiva on 28-02-2024 at 11:54
 ***/
@Composable
fun ProfileEditContent(paddingValues: PaddingValues,
                       navHostController: NavHostController,
                       viewModel: ProfileEditViewModel = hiltViewModel()){

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth()
        /*.wrapContentHeight()
        .background(Color.Red),*/
    ) {

        BoxHeader(viewModel)

        CardForm(viewModel, navHostController)

    }

}

@Composable
fun CardForm(viewModel: ProfileEditViewModel, navHostController: NavHostController){

    val state = viewModel.state


    Card(backgroundColor = DarkGray700,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 180.dp)
        //.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "Editar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Por favor ingresa estos datos para continuar",
                fontSize = 12.sp,
                color = Color.Gray
            )
            //Spacer(modifier = Modifier.height(25.dp))
            DefaultTextField(
                modifier = Modifier.padding(top = 25.dp),
                value = state.userName,
                onValueChange = {
                    viewModel.onUserNameInput(it)
                },
                label = "Nombre de usuario",
                icon =  Icons.Default.Person,
                errorMsg = viewModel.userNameErrorMsg,
                validateField = {
                    viewModel.validateUserName()
                }
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(top = 15.dp, bottom = 40.dp)
                    .padding(vertical = 15.dp),
                text = "Actualizar Datos",
                onClick = {
                    viewModel.saveImage()

                    var image = ""
                    if (viewModel.user.image != ""){
                        image = viewModel.user.image
                        viewModel.onUpdate(image)
                    }
                    //if (viewModel.user.image != "") image = viewModel.user.image

                }
            )
        }

    }
}

@Composable
fun BoxHeader(viewModel: ProfileEditViewModel){

    viewModel.resultingActivityHandler.handle()

    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )
    Box(modifier = Modifier
        .height(250.dp)
        .background(Brown500)
        .fillMaxWidth()
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            if (viewModel.imageUri != ""){
                AsyncImage(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            dialogState.value = true
                        },
                    model = viewModel.imageUri,
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop)

            }else if (viewModel.user.image != ""){
                AsyncImage(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            dialogState.value = true
                        },
                    model = viewModel.user.image,
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop)
            }else{
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .clickable {
                            dialogState.value = true
                        },
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Imagen de usuario"
                )
            }
        }

    }
}