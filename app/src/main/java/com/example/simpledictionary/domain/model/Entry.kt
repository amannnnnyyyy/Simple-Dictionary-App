package com.example.simpledictionary.domain.model

import com.example.simpledictionary.data.remote.dto.LanguageDto

data class Entry(
    val language: Language?,
    val antonyms: List<String>?,
    val forms: List<Form>?,
    val partOfSpeech: String?,
    val pronunciations: List<Pronunciation>?,
    val senses: List<Sense>?,
    val synonyms: List<String>?
)
