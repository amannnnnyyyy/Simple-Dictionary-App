package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Entry
import com.example.simpledictionary.domain.model.WordDetail

data class WordDetailDto(
    val entries: List<EntryDto>?,
    val source: SourceDto?,
    val word: String?
)


fun WordDetailDto.toWordDetail(): WordDetail {
    val entryList = mutableListOf<Entry>()
    entries?.forEach { entryDto ->
        entryList.add(entryDto.toEntry())
    }

    return WordDetail(
        entries = entryList,
        word = word,
        source = source?.toSource()
    )
}