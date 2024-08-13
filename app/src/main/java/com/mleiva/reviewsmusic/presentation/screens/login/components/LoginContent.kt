package com.mleiva.reviewsmusic.presentation.screens.login.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.presentation.components.DefaultButton
import com.mleiva.reviewsmusic.presentation.components.DefaultTextField
import com.mleiva.reviewsmusic.presentation.screens.login.LoginViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500
import com.mleiva.reviewsmusic.presentation.ui.theme.DarkGray700
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.login.components
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:31
 ***/
@Composable
fun LoginContent(paddingValues: PaddingValues, navHostController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){


    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth()
        /*.wrapContentHeight()
        .background(Color.Red),*/
    ) {

        BoxHeader()

        CardForm(viewModel, navHostController)

    }
}

@Composable
fun BoxHeader(){
    Box(modifier = Modifier
        .height(300.dp)
        .background(Brown500)
        .fillMaxWidth()
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(130.dp),
                painter = painterResource(id = R.drawable.cd_and_vinil),
                contentDescription = "Control de Xbox 360"
            )
        }

    }
}

@Composable
fun CardForm(viewModel: LoginViewModel, navHostController: NavHostController){

    //val loginFlow = viewModel.loginFlow.collectAsState()
    val state = viewModel.state

    Card(backgroundColor = DarkGray700,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 200.dp)
        //.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "LOGIN",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Por favor inIcia sesión para continuar",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultTextField(
                modifier = Modifier.padding(top = 25.dp),
                value = state.email,
                onValueChange = {
                    viewModel.onEmailInput(it)
                },
                label = "Correo  electrónico",
                icon =  Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = viewModel.emailErrorMsg,
                validateField = {
                    viewModel.validateEmail()
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.padding(top = 15.dp),
                value = state.password,
                onValueChange = {
                    viewModel.onPasswordInput(it)
                },
                label = "Contraseña",
                icon =  Icons.Default.Lock,
                hideText = true,
                errorMsg = viewModel.passwordErrorMsg,
                validateField = {
                    viewModel.validatePassword()
                }
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                text = "INICIAR SESION",
                onClick = {
                    viewModel.login()
                    Log.d("LoginContent", "E")
                },
                enabled = viewModel.isEnabledLoginButton
            )

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginContentPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            //(rememberNavController())
            LoginContent(paddingValues = PaddingValues(), (rememberNavController()))
        }
    }
}