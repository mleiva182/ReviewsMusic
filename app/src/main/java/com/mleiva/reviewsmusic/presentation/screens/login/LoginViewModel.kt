package com.mleiva.reviewsmusic.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.login
 * Creted by: Marcelo Leiva on 26-02-2024 at 15:32
 ***/
@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {


    //STATE FORM
    var state by mutableStateOf(LoginState())
        private set

    //var email by mutableStateOf("")
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrorMsg by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMsg by mutableStateOf("")
        private set


    var isEnabledLoginButton = false

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    val currentUser = authUseCase.getCurrentUser()

    init {
        if(currentUser!=null){
            loginResponse = Response.Success(currentUser)
        }
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCase.login(state.email, state.password)
        loginResponse = result
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrorMsg = ""
        }else{
            isEmailValid = false
            emailErrorMsg = "El email no es valido"
        }

        enabledLoginButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrorMsg = ""
        }else{
            isPasswordValid = false
            passwordErrorMsg = "Debe ingresar 6 caracteres"
        }

        enabledLoginButton()
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

}