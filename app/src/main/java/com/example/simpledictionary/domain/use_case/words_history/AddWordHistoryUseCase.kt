package com.example.simpledictionary.domain.use_case.words_history

import android.util.Log
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.db.DictionaryDao
import com.example.simpledictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

data class AddWordHistoryUseCase@Inject constructor(
    private val repository: DictionaryDao
){
    operator fun invoke(word: WordDetail): Flow<Resource<WordDetail>> = flow {
        try {
            Log.i("FireStoreAdd", "Result: started adding in usecase")

            emit(Resource.Loading())
            repository.addWord(word)
            emit(Resource.Success(word))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred!"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}
