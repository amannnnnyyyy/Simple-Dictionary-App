package com.example.simpledictionary.domain.model

data class Entry(
    val antonyms: List<String>?,
    val forms: List<Form>?,
    val partOfSpeech: String?,
    val pronunciations: List<Pronunciation>?,
    val senses: List<Sense>?,
    val synonyms: List<String>?
)
