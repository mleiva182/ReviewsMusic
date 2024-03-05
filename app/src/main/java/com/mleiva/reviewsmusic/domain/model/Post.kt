package com.mleiva.reviewsmusic.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.domain.model
 * Creted by: Marcelo Leiva on 05-03-2024 at 14:44
 ***/
data class Post(
                var id: String = "",
                var name: String = "",
                var description: String = "",
                var category: String = "",
                var idUser: String = "",
                var image: String = "",
                var user: User? = null,
                var likes: ArrayList<String> = ArrayList()
){
    fun toJson(): String = Gson().toJson(Post(
        id,
        name,
        description,
        category,
        if(image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
        idUser,
        User(
            id = user?.id ?: "",
            userName = user?.userName ?: "",
            email = user?.email ?: "",
            image =
            if(!user?.image.isNullOrBlank())
                URLEncoder.encode(user?.image, StandardCharsets.UTF_8.toString())
            else ""
        ),
        likes
    ))

    companion object{
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)
    }
}
