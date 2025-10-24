package com.example.simpledictionary.data.remote

import retrofit2.http.GET

interface WordApi {
    @GET("/api/v1")
    suspend fun getWordDetail()
}