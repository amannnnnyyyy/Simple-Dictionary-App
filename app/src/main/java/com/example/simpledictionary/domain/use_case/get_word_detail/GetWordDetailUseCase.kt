package com.example.simpledictionary.domain.use_case.get_word_detail

import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.data.remote.dto.toWordDetail
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.domain.repository.WordDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetWordDetailUseCase @Inject constructor(
    private val repository: WordDetailRepository
) {
    operator fun invoke(word:String): Flow<Resource<List<WordDetail>>> = flow {
        try {

            emit(Resource.Loading())
            val wordDetail = repository.getWordDetail(word)
            emit(Resource.Success(wordDetail.map { it.toWordDetail() }))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred!"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}