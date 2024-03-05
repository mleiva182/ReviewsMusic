package com.mleiva.reviewsmusic.domain.use_cases.users

import com.mleiva.reviewsmusic.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.use_cases.users
 * Creted by: Marcelo Leiva on 02-03-2024 at 16:59
 ***/
class SaveImage @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(file: File) = repository.saveImage(file)

}