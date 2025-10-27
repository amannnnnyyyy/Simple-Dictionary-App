package com.example.simpledictionary.domain.repository

import com.example.simpledictionary.data.remote.dto.WordDetailDto

interface WordDetailRepository {
    suspend fun getWordDetail(word:String): WordDetailDto
}