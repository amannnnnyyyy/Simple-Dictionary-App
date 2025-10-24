package com.example.simpledictionary.data.remote.dto

data class SenseDto(
    val antonyms: List<String>?,
    val definition: String?,
    val examples: List<String>?,
    val quotes: List<QuoteDto>?,
    val subsenses: List<String>?,
    val synonyms: List<String>?,
    val tags: List<String>?
)