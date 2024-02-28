package com.mleiva.reviewsmusic.domain.use_cases.users

import com.mleiva.reviewsmusic.domain.repository.UsersRepository
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.users
 * Creted by: Marcelo Leiva on 27-02-2024 at 17:12
 ***/
class GetUserById @Inject constructor(private val repository: UsersRepository) {


    operator fun invoke(id: String) = repository.getUserById(id)

}