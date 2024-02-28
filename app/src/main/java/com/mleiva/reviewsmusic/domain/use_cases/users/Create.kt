package com.mleiva.reviewsmusic.domain.use_cases.users

import com.mleiva.reviewsmusic.domain.model.User
import com.mleiva.reviewsmusic.domain.repository.UsersRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.users
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:28
 ***/
class Create @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(user: User) = repository.create(user)

}