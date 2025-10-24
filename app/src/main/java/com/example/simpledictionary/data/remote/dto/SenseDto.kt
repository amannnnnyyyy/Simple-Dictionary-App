package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Quote
import com.example.simpledictionary.domain.model.Sense

data class SenseDto(
    val antonyms: List<String>?,
    val definition: String?,
    val examples: List<String>?,
    val quotes: List<QuoteDto>?,
    val subsenses: List<String>?,
    val synonyms: List<String>?,
    val tags: List<String>?
)

fun SenseDto.toSense(): Sense{
    val quoteList = mutableListOf<Quote>()
    quotes?.forEach { quoteDto ->
        quoteList.add(quoteDto.toQuote())
    }

    return Sense(
        antonyms = antonyms,
        definition = definition,
        examples = examples,
        quotes = quoteList,
        subsenses = subsenses,
        synonyms = synonyms,
        tags = tags
    )
}