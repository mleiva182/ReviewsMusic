package com.mleiva.reviewsmusic.presentation.screens.posts

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
 * From: com.mleiva.reviewsmusic.presentation.screens.posts
 * Creted by: Marcelo Leiva on 06-03-2024 at 12:12
 ***/
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases,
    private val authUseCase: AuthUseCase
): ViewModel() {

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)
    var deleteLikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser = authUseCase.getCurrentUser()

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {

        postsResponse = Response.Loading
        postsUseCases.getPosts().collect(){response ->
            postsResponse = response
        }

    }

    fun like(idPost: String, idUser: String) = viewModelScope.launch {

        likeResponse = Response.Loading
        val result = postsUseCases.likePost(idPost, idUser)
        likeResponse = result


    }

    fun deleteLike(idPost: String, idUser: String) = viewModelScope.launch {

        likeResponse = Response.Loading
        val result = postsUseCases.deleteLikePost(idPost, idUser)
        likeResponse = result


    }


}