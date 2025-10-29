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
    private val getWordHistoryUseCase: GetWordHistoryUseCase,
    private val addWordHistoryUseCase: AddWordHistoryUseCase
): ViewModel() {

    private val _state = mutableStateOf<Resource<List<WordDetail>>>(Resource.Loading())
    val state: State<Resource<List<WordDetail>>> = _state

    val noSimilarDataNotifier = mutableStateOf(false)

    private val _stateForFiltering = mutableStateOf<Resource<List<WordDetail>>>(Resource.Loading())
    val stateForFiltering: State<Resource<List<WordDetail>>> = _stateForFiltering

    init {
        viewModelScope.launch {
            getWordHistoryUseCase().collectLatest { wordDetailList ->
                Log.i("FireStoreFetch", "Result: ${wordDetailList.data}")
                _state.value = wordDetailList
            }
        }
    }

    fun addWordDetail(wordDetail: WordDetail){
        Log.i("FireStoreAdd", "Result: started it")

        viewModelScope.launch(Dispatchers.IO) {
            addWordHistoryUseCase(wordDetail).collectLatest { addedWord->
                Log.i("FireStoreAdd", "Result: started $addedWord")
            }
        }

    }

    fun filterWordDetailsByWord(word:String){
        val res = state.value
        when(res){
            is Resource.Success -> {
                val data = res.data
                val filteredData = data?.filter { wordDetail ->
                    Log.i("Searching", "${wordDetail.word} : $word")
                    wordDetail.word?.startsWith(word,ignoreCase = true)?:true
                }
                noSimilarDataNotifier.value = (filteredData?.size?:0)==0
                _stateForFiltering.value = Resource.Success(filteredData?: listOf())
            }
            else->{}
        }
    }

    fun getWord(wordDetail: WordDetail){
        Log.i("FireStoreAdd", "Result: started it")

        viewModelScope.launch(Dispatchers.IO) {
            addWordHistoryUseCase(wordDetail).collectLatest { addedWord->
                Log.i("FireStoreAdd", "Result: started $addedWord")
            }
        }

    }

}