package com.mleiva.reviewsmusic.presentation.screens.new_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.posts.PostUseCases
import com.mleiva.reviewsmusic.utils.ComposeFileProvider
import com.optic.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.new_post
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:42
 ***/
@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsUseCases: PostUseCases,
    private val authUseCase: AuthUseCase
): ViewModel() {

    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val currentUser = authUseCase.getCurrentUser()

    var state by mutableStateOf(NewPostState())

    fun onNameInput(name: String){
        state = state.copy(name = name)
    }
    fun onCategoryInput(category: String){
        state = state.copy(category = category)
    }
    fun onDescriptionInput(description: String){
        state = state.copy(description = description)
    }
    fun onImageInput(image: String){
        state = state.copy(image = image)
    }

    val radioOptions = listOf(
        CategoryRadioButton("Rock", R.drawable.icon_rock),
        CategoryRadioButton("Metal", R.drawable.icon_metal),
        CategoryRadioButton("Pop", R.drawable.icon_pop),
        CategoryRadioButton("Hip Hop", R.drawable.icon_hip_hop),
        CategoryRadioButton("Urban", R.drawable.icon_urban),
        CategoryRadioButton("Other", R.drawable.icon_other)
    )

    //FILE
    var file: File? = null
    var imageUri by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()

    fun createPost(post: Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = postsUseCases.create(post, file!!)
        createPostResponse = result
    }

    fun onNewPost(){
        val post = Post(
            name = state.name,
            description = state.description,
            category = state.category,
            idUser = currentUser?.uid!!
        )
        createPost(post)
        Log.d("NewPostViewModel", "name: ${state.name}")
        Log.d("NewPostViewModel", "category: ${state.category}")
        Log.d("NewPostViewModel", "description: ${state.description}")
        Log.d("NewPostViewModel", "image: ${state.image}")
    }

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

    fun clearForm(){
        state = state.copy(
            name = "",
            category = "",
            description = "",
            image = "",
        )
        createPostResponse = null
    }
}


data class CategoryRadioButton(
    var category: String,
    val image: Int
)