package com.example.simpledictionary.data.remote.dto

data class WordDetailDto(
    val entries: List<EntryDto>?,
    val source: SourceDto?,
    val word: String?
)