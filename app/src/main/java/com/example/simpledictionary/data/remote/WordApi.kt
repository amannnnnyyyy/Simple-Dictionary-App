package com.example.simpledictionary.data.remote

import com.example.simpledictionary.data.remote.dto.WordDetailDto
import retrofit2.http.GET

interface WordApi {
    @GET("/api/v1")
    suspend fun getWordDetail(): List<WordDetailDto>
}