package com.example.simpledictionary.presentation.word_detail

import androidx.lifecycle.ViewModel
import com.example.simpledictionary.domain.use_case.get_word_detail.GetWordDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val getWordDetailUseCase: GetWordDetailUseCase
): ViewModel(){


}