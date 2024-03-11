package com.mleiva.reviewsmusic.presentation.screens.detail_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mleiva.reviewsmusic.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.presentation.screens.detail_post
 * Creted by: Marcelo Leiva on 11-03-2024 at 10:20
 ***/
@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)

}