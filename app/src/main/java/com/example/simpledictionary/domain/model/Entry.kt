package com.example.simpledictionary.domain.model

import com.example.simpledictionary.data.remote.dto.LanguageDto

data class Entry(
    val language: Language?=null,
    val antonyms: List<String>?=null,
    val forms: List<Form>?=null,
    val partOfSpeech: String?=null,
    val pronunciations: List<Pronunciation>?=null,
    val senses: List<Sense>?=null,
    val synonyms: List<String>?=null
)
