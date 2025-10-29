package com.example.simpledictionary.domain.use_case.words_history

import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

data class GetWordHistoryUseCase@Inject constructor(
    private val repository: DictionaryDao
){
    operator fun invoke(): Flow<Resource<List<WordDetail>>>{
        return repository.getWords()
            .map<List<WordDetail>, Resource<List<WordDetail>>> { wordDetails ->
                Resource.Success(wordDetails)
            }
            .onStart {
                emit(Resource.Loading())
            }
            .catch { exception ->
                when (exception) {
                    is HttpException -> emit(Resource.Error(exception.localizedMessage ?: "An unexpected error occurred!"))
                    is IOException -> emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
                    else -> emit(Resource.Error(exception.message ?: "An unknown error occurred"))
                }
            }
    }
}
