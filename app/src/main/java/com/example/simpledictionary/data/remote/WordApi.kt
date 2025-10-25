package com.example.simpledictionary.data.remote

import com.example.simpledictionary.data.remote.dto.WordDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {
    @GET("/api/v1/entries/{lang}/{word}")
    suspend fun getWordDetail(@Path("word") word:String,@Path("lang") lang:String="en"): List<WordDetailDto>
}