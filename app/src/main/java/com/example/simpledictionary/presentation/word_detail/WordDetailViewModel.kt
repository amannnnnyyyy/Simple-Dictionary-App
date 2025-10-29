package com.example.simpledictionary.presentation.word_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.domain.use_case.word_detail_online.GetWordDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val getWordDetailUseCase: GetWordDetailUseCase
): ViewModel(){

    private val _state = mutableStateOf(WordDetailState())
    val state: State<WordDetailState> = _state

    fun getWordDetail(word:String){
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
                    _state.value = WordDetailState(wordDetails = result.data?: WordDetail(null, null, null))
                    Log.i("Database_fetched", "success: ${_state.value}")
                }
            }
        }.launchIn(viewModelScope)
    }
}