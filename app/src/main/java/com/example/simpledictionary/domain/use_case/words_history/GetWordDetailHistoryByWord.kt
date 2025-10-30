package com.example.simpledictionary.domain.use_case.words_history

import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

data class GetWordDetailHistoryByWord @Inject constructor(
    private val repository: DictionaryDao
){
    operator fun invoke(word:String): Flow<Resource<WordDetail>> = flow {
            try {
                emit(Resource.Loading())
                val wordDetail = repository.getWord(word)
                wordDetail?.let{emit(Resource.Success(it))}

            }  catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred!"))
            }catch (e: IOException){
                emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
            }
    }
}
