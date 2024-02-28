package com.mleiva.reviewsmusic.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.signup
 * Creted by: Marcelo Leiva on 26-02-2024 at 19:59
 ***/
@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCase: AuthUseCase, private val usersUseCases: UsersUseCases) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    var isUserNameValid by mutableStateOf(false)
        private set
    var userNameErrorMsg by mutableStateOf("")
        private set

    var isConfirmPasswordValid by mutableStateOf(false)
        private set
    var confirmPasswordErrorMsg by mutableStateOf("")
        private set

    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrorMsg by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMsg by mutableStateOf("")
        private set

    var isEnabledLoginButton = false

    var singInResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }
    fun onUserNameInput(userName: String){
        state = state.copy(userName = userName)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignUp(){
        user.userName = state.userName
        user.email = state.email
        user.password = state.password
        signUp(user)
    }

    fun signUp(user: User) = viewModelScope.launch {
        singInResponse = Response.Loading
        val result = authUseCase.signUp(user)
        singInResponse = result
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCase.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }


    fun validateUserName(){
        if(state.userName.length >= 5){
            isUserNameValid = true
            userNameErrorMsg = ""
        }else{
            isUserNameValid = false
            userNameErrorMsg = "Debe ingresar al menos 5 caracteres"
        }

        enabledSingUpButton()
    }

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            isConfirmPasswordValid = true
            confirmPasswordErrorMsg = ""
        }else{
            isConfirmPasswordValid = false
            confirmPasswordErrorMsg = "Las contraseñas no coinciden"
        }

        enabledSingUpButton()
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrorMsg = ""
        }else{
            isEmailValid = false
            emailErrorMsg = "El email no es valido"
        }

        enabledSingUpButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrorMsg = ""
        }else{
            isPasswordValid = false
            passwordErrorMsg = "Debe ingresar 6 caracteres"
        }

        enabledSingUpButton()
    }

    fun enabledSingUpButton(){
        isEnabledLoginButton =
            isUserNameValid &&
                    isEmailValid &&
                    isPasswordValid &&
                    isConfirmPasswordValid
    }

}