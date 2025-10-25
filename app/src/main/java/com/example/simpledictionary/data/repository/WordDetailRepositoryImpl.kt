package com.example.simpledictionary.data.repository

import com.example.simpledictionary.data.remote.WordApi
import com.example.simpledictionary.data.remote.dto.WordDetailDto
import com.example.simpledictionary.domain.repository.WordDetailRepository
import javax.inject.Inject

data class WordDetailRepositoryImpl @Inject constructor(val api: WordApi): WordDetailRepository{

    override suspend fun getWordDetail(word: String): List<WordDetailDto> = api.getWordDetail(word)

}
