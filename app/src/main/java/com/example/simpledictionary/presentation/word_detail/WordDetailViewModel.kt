package com.example.simpledictionary.presentation.word_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.use_case.get_word_detail.GetWordDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val getWordDetailUseCase: GetWordDetailUseCase
): ViewModel(){

    private val _state = mutableStateOf(WordDetailState())
    val state: State<WordDetailState> = _state


    fun getWordDetail(word:String){
        getWordDetailUseCase(word).onEach { result->
            when(result){
                is Resource.Error<*> -> {
                    _state.value = WordDetailState(error = result.message?:"An Unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = WordDetailState(wordDetails = result.data?:emptyList())
                }
                is Resource.Success -> {
                    _state.value = WordDetailState(isLoading = true)
                }
            }
        }
    }
}