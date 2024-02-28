package com.mleiva.reviewsmusic.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.model.repository.use_cases
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:12
 ***/
data class User(

    var id: String = "",
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var image: String = ""
){
    fun toJson(): String = Gson().toJson(User(
        id,
        userName,
        email,
        password,
        if(image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else ""
    ))

    companion object{
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }

}