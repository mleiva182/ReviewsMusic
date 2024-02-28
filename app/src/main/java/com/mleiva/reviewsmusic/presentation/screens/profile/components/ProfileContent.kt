package com.mleiva.reviewsmusic.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.presentation.MainActivity
import com.mleiva.reviewsmusic.presentation.components.DefaultButton
import com.mleiva.reviewsmusic.presentation.screens.login.LoginViewModel
import com.mleiva.reviewsmusic.presentation.screens.profile.ProfileViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.home.components
 * Creted by: Marcelo Leiva on 27-02-2024 at 16:56
 ***/
@Composable
fun ProfileContent(paddingValues: PaddingValues,
                   navHostController: NavHostController,
                   viewModel: ProfileViewModel = hiltViewModel()){

    val activity = LocalContext.current as? Activity

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Bienvenido",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    modifier = Modifier
                        .size(115.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = viewModel.userData.userName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(20.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            icon = Icons.Default.Edit,
            onClick = {
                
            }
        )

        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar sesion",
            icon = Icons.Default.Close,
            onClick = {
                viewModel.logOut()
                /*navHostController.navigate(AuthScreen.Login.route){
                    popUpTo(Graph.HOME){inclusive = true}
                }*/
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileContent() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ProfileContent(paddingValues = PaddingValues(), rememberNavController())
        }
    }
}