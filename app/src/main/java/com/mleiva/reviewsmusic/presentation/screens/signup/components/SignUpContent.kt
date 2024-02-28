package com.mleiva.reviewsmusic.presentation.screens.signup.components

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
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
import com.mleiva.reviewsmusic.presentation.screens.signup.SignUpViewModel
import com.mleiva.reviewsmusic.presentation.ui.theme.Brown500
import com.mleiva.reviewsmusic.presentation.ui.theme.DarkGray700
import com.mleiva.reviewsmusic.presentation.ui.theme.ReviewsMusicTheme

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.signup.components
 * Creted by: Marcelo Leiva on 26-02-2024 at 19:58
 ***/
@Composable
fun SignUpContent(paddingValues: PaddingValues, navHostController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()){
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
        .height(250.dp)
        .background(Brown500)
        .fillMaxWidth()
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                modifier = Modifier.height(120.dp),
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Imagen de usuario"
            )
        }

    }
}

@Composable
fun CardForm(viewModel: SignUpViewModel, navHostController: NavHostController) {

    val state = viewModel.state

    Card(
        backgroundColor = DarkGray700,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 180.dp)
        //.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "REGISTRO",
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
                icon = Icons.Default.Person,
                errorMsg = viewModel.userNameErrorMsg,
                validateField = {
                    viewModel.validateUserName()
                }
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 0.dp),
                value = state.email,
                onValueChange = {
                    viewModel.onEmailInput(it)
                },
                label = "Correo electronico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = viewModel.emailErrorMsg,
                validateField = {
                    viewModel.validateEmail()
                }
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 0.dp),
                value = state.password,
                onValueChange = {
                    viewModel.onPasswordInput(it)
                },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                hideText = true,
                errorMsg = viewModel.passwordErrorMsg,
                validateField = {
                    viewModel.validatePassword()
                }
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 0.dp),
                value = state.confirmPassword,
                onValueChange = {
                    viewModel.onConfirmPasswordInput(it)
                },
                label = "Confirmar Contraseña",
                icon = Icons.Outlined.Lock,
                hideText = true,
                errorMsg = viewModel.confirmPasswordErrorMsg,
                validateField = {
                    viewModel.validateConfirmPassword()
                }
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                text = "REGISTRARSE",
                onClick = {
                    viewModel.onSignUp()
                },
                enabled = viewModel.isEnabledLoginButton
            )
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpContentPreview() {
    ReviewsMusicTheme(
        darkTheme = true
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SignUpContent(paddingValues = PaddingValues(), (rememberNavController()))
        }
    }
}