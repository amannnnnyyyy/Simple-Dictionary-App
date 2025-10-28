package com.example.simpledictionary.domain.use_case.get_words_history

import android.util.Log
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

data class GetWordHistoryUseCase@Inject constructor(
    private val repository: DictionaryDao
){
    operator fun invoke(): Flow<Resource<List<WordDetail>>> = flow {
        try {
            Log.i("Database_fetched", "getWordDetail: started usecase")

            emit(Resource.Loading())
            repository.getWords().collectLatest { wordDetails ->
                emit(Resource.Success(wordDetails))
            }
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred!"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}
