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

data class GetWordDetailHistoryByWord @Inject constructor(
    private val repository: DictionaryDao
){
    operator fun invoke(word:String): Flow<Resource<WordDetail>> = flow {
            try {
                emit(Resource.Loading())
                Log.i("Check word detail", "WordDetailScreen: loading in use case")

                val wordDetail = repository.getWord(word)
                Log.i("Check word detail", "WordDetailScreen: success gotten back data in usecase $wordDetail")

                wordDetail?.let{emit(Resource.Success(it))}?:emit(Resource.Error(message = "Word not found in database"))

            }  catch (e: HttpException){
                Log.i("Check word detail", "WordDetailScreen: error in usecase first")

                emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred!"))
            }catch (e: IOException){
                Log.i("Check word detail", "WordDetailScreen: error in usecase second")
                emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
            }
    }
}
