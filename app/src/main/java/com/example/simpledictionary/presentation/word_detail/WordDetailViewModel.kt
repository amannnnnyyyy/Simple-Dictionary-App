package com.example.simpledictionary.presentation.word_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.domain.use_case.word_detail_online.GetWordDetailUseCase
import com.example.simpledictionary.domain.use_case.words_history.AddWordHistoryUseCase
import com.example.simpledictionary.domain.use_case.words_history.GetWordDetailHistoryByWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _state: MutableStateFlow<Resource<WordDetail>> = MutableStateFlow<Resource<WordDetail>>(Resource.Loading())
    val state: StateFlow<Resource<WordDetail>> = _state

    fun getWordDetailFromRemote(word: String) {
        getWordDetailUseCase(word).onEach { result ->
            _state.value = result
        }.launchIn(viewModelScope)
    }

     fun getWordDetailLocally(word:String): Resource<WordDetail>{
        getWordHistoryByWordUseCase(word).onEach { result->
            when(result){
                is Resource.Error<*> -> {
                    getWordDetailFromRemote(word)
                }
                is Resource.Loading -> {
                    Log.i("Check word detail", "WordDetailScreen: loading in viewmodel")
                    _state.value = Resource.Loading()
                }
                is Resource.Success -> {
                    Log.i("Check word detail", "WordDetailScreen: success in viewmodel")
                    _state.value = Resource.Success(data = result.data?: WordDetail(null, null, null))
                }
            }
        }.launchIn(viewModelScope)
         return _state.value
    }


    fun addWordDetail(wordDetail: WordDetail){
        wordDetail.fromDb = true
        viewModelScope.launch(Dispatchers.IO) {
            addWordHistoryUseCase(wordDetail).collectLatest { addedWord->
                when(addedWord){
                    is Resource.Error<*> -> {}
                    is Resource.Loading<*> -> {}
                    is Resource.Success<*> -> _state.value = Resource.Success(wordDetail)
                }
            }
        }
    }
}