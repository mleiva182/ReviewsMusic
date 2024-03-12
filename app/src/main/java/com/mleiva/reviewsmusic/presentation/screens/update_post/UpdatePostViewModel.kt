package com.mleiva.reviewsmusic.presentation.screens.update_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.reviewsmusic.R
import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.posts.PostsUseCases
import com.mleiva.reviewsmusic.presentation.screens.new_post.CategoryRadioButton
import com.mleiva.reviewsmusic.utils.ComposeFileProvider
import com.optic.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.update_post
 * Creted by: Marcelo Leiva on 12-03-2024 at 15:38
 ***/
@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val postsUseCases: PostsUseCases,
    private val authUseCase: AuthUseCase
): ViewModel() {

    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val currentUser = authUseCase.getCurrentUser()

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postsUseCases.updatePost(post, file)
        updatePostResponse = result
    }

    val radioOptions = listOf(
        CategoryRadioButton("Rock", R.drawable.icon_rock),
        CategoryRadioButton("Metal", R.drawable.icon_metal),
        CategoryRadioButton("Pop", R.drawable.icon_pop),
        CategoryRadioButton("Hip Hop", R.drawable.icon_hip_hop),
        CategoryRadioButton("Urban", R.drawable.icon_urban),
        CategoryRadioButton("Other", R.drawable.icon_other)
    )

    var state by mutableStateOf(UpdatePostState())

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

    //FILE
    var file: File? = null
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

    //ARGUMENTS
    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)

    init {
        state = state.copy(
            name = post.name,
            description = post.description,
            image = post.image,
            category = post.category,
        )
    }

    fun onUpdatePost(){
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = post.image,
            idUser = currentUser?.uid!!
        )
        updatePost(post)
        Log.d("NewPostViewModel", "name: ${state.name}")
        Log.d("NewPostViewModel", "category: ${state.category}")
        Log.d("NewPostViewModel", "description: ${state.description}")
        Log.d("NewPostViewModel", "image: ${state.image}")
    }

    fun clearForm(){
        /*state = state.copy(
            name = "",
            category = "",
            description = "",
            image = "",
        )*/
        updatePostResponse = null
    }

}

data class CategoryRadioButton(
    var category: String,
    val image: Int
)