package com.example.simpledictionary.domain.db

import com.example.simpledictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow

interface DictionaryDao {
    suspend fun addWord(wordDetail: WordDetail)
    suspend fun getWord(word:String): WordDetail?
    suspend fun getWords(): Flow<List<WordDetail>>
}