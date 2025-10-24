package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Quote

data class QuoteDto(
    val reference: String?,
    val text: String?
)


fun QuoteDto.toQuote(): Quote{
    return Quote(
        reference = reference,
        text = text
    )
}