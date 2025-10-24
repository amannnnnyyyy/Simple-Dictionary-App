package com.example.simpledictionary.data.remote.dto

data class Entry(
    val antonyms: List<String>?,
    val forms: List<Form>?,
    val language: Language?,
    val partOfSpeech: String?,
    val pronunciations: List<Pronunciation>?,
    val senses: List<Sense>?,
    val synonyms: List<String>?
)