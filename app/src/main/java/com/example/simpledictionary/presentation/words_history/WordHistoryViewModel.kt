package com.example.simpledictionary.presentation.words_history

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.domain.use_case.words_history.AddWordHistoryUseCase
import com.example.simpledictionary.domain.use_case.words_history.GetWordHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class WordHistoryViewModel@Inject constructor(
    private val getWordHistoryUseCase: GetWordHistoryUseCase
): ViewModel() {

    private val _state = mutableStateOf<Resource<List<WordDetail>>>(Resource.Loading())
    val state: State<Resource<List<WordDetail>>> = _state

    val noSimilarDataNotifier = mutableStateOf(false)

    private val _stateForFiltering = mutableStateOf<Resource<List<WordDetail>>>(Resource.Loading())
    val stateForFiltering: State<Resource<List<WordDetail>>> = _stateForFiltering

    init {
        viewModelScope.launch {
            getWordHistoryUseCase().collectLatest { wordDetailList ->
                _state.value = wordDetailList
            }
        }
    }

    fun filterWordDetailsByWord(word:String){
        val res = state.value
        when(res){
            is Resource.Success -> {
                val data = res.data
                val filteredData = data?.filter { wordDetail ->
                    wordDetail.word?.startsWith(word,ignoreCase = true)?:true
                }
                noSimilarDataNotifier.value =
                    if (word.isBlank()) false
                    else if ((filteredData?.size?:0)==0) true
                    else filteredData?.none{it.word == word}?:false
                _stateForFiltering.value = Resource.Success(filteredData?: listOf())
            }
            else->{}
        }
    }
}