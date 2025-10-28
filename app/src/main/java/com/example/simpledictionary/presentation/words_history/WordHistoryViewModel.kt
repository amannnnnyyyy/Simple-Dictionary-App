package com.example.simpledictionary.presentation.words_history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledictionary.domain.use_case.get_word_detail.GetWordDetailUseCase
import com.example.simpledictionary.domain.use_case.get_words_history.GetWordHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordHistoryViewModel@Inject constructor(
    private val getWordHistoryUseCase: GetWordHistoryUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            getWordHistoryUseCase().collectLatest {
                Log.i("FireStoreFetch", "Result: $it")
            }
        }
    }
}