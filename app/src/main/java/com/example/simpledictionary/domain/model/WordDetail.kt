package com.example.simpledictionary.domain.model

import androidx.annotation.Keep
import com.example.simpledictionary.data.remote.dto.SourceDto

@Keep
data class WordDetail(
    val entries: List<Entry>? = null,
    val word: String?=null,
    val source: Source?=null,
    var fromDb: Boolean?=null
)
