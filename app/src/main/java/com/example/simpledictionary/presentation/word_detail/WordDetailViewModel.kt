package com.example.simpledictionary.presentation.word_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledictionary.R
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.domain.use_case.word_detail_online.GetWordDetailUseCase
import com.example.simpledictionary.domain.use_case.words_history.AddWordHistoryUseCase
import com.example.simpledictionary.domain.use_case.words_history.GetWordDetailHistoryByWord
import com.example.simpledictionary.domain.use_case.words_history.GetWordHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val getWordDetailUseCase: GetWordDetailUseCase,
    private val addWordHistoryUseCase: AddWordHistoryUseCase,
    private val getWordHistoryByWordUseCase: GetWordDetailHistoryByWord

): ViewModel(){

    private val _state = mutableStateOf(WordDetailState())
    val state = _state


    suspend fun getWordDetail(word:String){
        Log.i("Database_fetched", "getWordDetail: to call usecase")
        getWordDetailUseCase(word).onEach { result->
            when(result){
                is Resource.Error<*> -> {
                    _state.value = WordDetailState(error = result.message?:"An Unexpected error occurred!")
                    Log.i("Database_fetched", "error: ${_state.value}")
                }
                is Resource.Loading -> {
                    _state.value = WordDetailState(isLoading = true)
                    Log.i("Database_fetched", "loading: ${_state.value}")
                }
                is Resource.Success -> {
                    _state.value = WordDetailState(wordDetails = result.data?: WordDetail(null, null, null), success = true)
                    Log.i("choosingSource", "success: ${_state.value.success}")
                }
            }
        }
    }


     fun getWordDetailLocally(word:String): Resource<WordDetail>{
         var resFinal: Resource<WordDetail> = Resource.Loading()
        getWordHistoryByWordUseCase(word).onEach { result->
            when(result){
                is Resource.Error<*> -> {
                    _state.value = WordDetailState(error = result.message?:"An Unexpected error occurred!")
                    resFinal = Resource.Error<WordDetail>(_state.value.error)
                }
                is Resource.Loading -> {
                    _state.value = WordDetailState(isLoading = true)
                    resFinal = Resource.Loading<WordDetail>()
                }
                is Resource.Success -> {
                    _state.value = WordDetailState(wordDetails = result.data?: WordDetail(null, null, null), success = true)
                    resFinal = Resource.Success<WordDetail>(_state.value.wordDetails)
                }
            }
        }.launchIn(viewModelScope)
         return resFinal
    }


    fun addWordDetail(wordDetail: WordDetail){
        wordDetail.fromDb = true
        viewModelScope.launch(Dispatchers.IO) {
            addWordHistoryUseCase(wordDetail).collectLatest { addedWord-> }
        }

    }
}