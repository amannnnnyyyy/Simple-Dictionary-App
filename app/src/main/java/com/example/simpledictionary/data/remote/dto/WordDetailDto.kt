package com.example.simpledictionary.data.remote.dto

data class WordDetailDto(
    val entries: List<Entry>?,
    val source: Source?,
    val word: String?
)