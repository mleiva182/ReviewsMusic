package com.mleiva.reviewsmusic.presentation.screens.profile_edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.use_cases.users.UsersUseCases
import com.mleiva.reviewsmusic.utils.ComposeFileProvider
import com.optic.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.profile_edit
 * Creted by: Marcelo Leiva on 28-02-2024 at 11:56
 ***/
@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
): ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set

    fun onUserNameInput(userName: String){
        state = state.copy(userName = userName)
    }

    var userNameErrorMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    //RESPONSE SAVE IMAGE
    var saveImageResponse by mutableStateOf<Response<String>>(Response.Success(""))
        private set
    //FILE
    var file: File? = null

    //IMAGE
    var imageUri by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            imageUri = result.toString()
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result != null) {
            imageUri = ComposeFileProvider.getPathFromBitmap(context, result!!)
            file = File(imageUri)
        }
    }

    init {
        state = state.copy(userName = user.userName)
    }

    fun saveImage() = viewModelScope.launch {
        if(file != null) {
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

    fun onUpdate(url: String){
        val myUser = User(
            id = user.id,
            userName = state.userName,
            image = url
        )
        update(myUser)
    }

    fun validateUserName(){
        if(state.userName.length >= 5){
            userNameErrorMsg = ""
        }else{
            userNameErrorMsg = "Debe ingresar al menos 5 caracteres"
        }
    }

}