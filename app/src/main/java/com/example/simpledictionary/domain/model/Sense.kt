package com.example.simpledictionary.domain.model

data class Sense(
    val antonyms: List<String>?,
    val definition: String?,
    val examples: List<String>?,
    val quotes: List<Quote>?,
    val subsenses: List<Sense>?,
    val synonyms: List<String>?,
    val tags: List<String>?
)
