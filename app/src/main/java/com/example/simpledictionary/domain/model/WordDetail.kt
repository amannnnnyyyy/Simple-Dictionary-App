package com.example.simpledictionary.domain.model

import com.example.simpledictionary.data.remote.dto.SourceDto

data class WordDetail(
    val entries: List<Entry>?,
    val word: String?,
    val source: Source?
    )
