package com.mleiva.reviewsmusic.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile
 * Creted by: Marcelo Leiva on 27-02-2024 at 16:57
 ***/
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCases: UsersUseCases): ViewModel() {

    var userData by mutableStateOf(User())
        private set //info no puede ser cambiada desde otra clase

    val currentUser = authUseCase.getCurrentUser()

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currentUser!!.uid).collect(){
            userData = it
        }
    }


    fun logOut(){
        authUseCase.logout()
    }

}