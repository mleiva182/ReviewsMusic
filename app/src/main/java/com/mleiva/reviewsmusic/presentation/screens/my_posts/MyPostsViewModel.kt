package com.mleiva.reviewsmusic.presentation.screens.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.reviewsmusic.domain.model.Post
import com.mleiva.reviewsmusic.domain.model.Response
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.posts.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.my_posts
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:33
 ***/
@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases,
    private val authUseCase: AuthUseCase
): ViewModel() {

    var postResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCase.getCurrentUser()

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {

        postResponse = Response.Loading
        currentUser?.let {
            postsUseCases.getPostsByIdUser(it.uid).collect(){response ->
                postResponse = response
            }
        }
    }

    fun delete(idPost: String) = viewModelScope.launch {

        deleteResponse = Response.Loading
        postsUseCases.deletePost

    }

}

