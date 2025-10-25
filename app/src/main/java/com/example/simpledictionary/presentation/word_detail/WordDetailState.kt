package com.example.simpledictionary.presentation.word_detail

import com.example.simpledictionary.domain.model.WordDetail

data class WordDetailState(
    val isLoading: Boolean = false,
    val wordDetails: List<WordDetail> = emptyList(),
    val error: String = ""
    )
