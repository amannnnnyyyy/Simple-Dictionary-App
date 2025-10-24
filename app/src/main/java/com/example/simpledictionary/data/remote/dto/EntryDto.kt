package com.example.simpledictionary.data.remote.dto

data class EntryDto(
    val antonyms: List<String>?,
    val forms: List<FormDto>?,
    val language: LanguageDto?,
    val partOfSpeech: String?,
    val pronunciations: List<PronunciationDto>?,
    val senses: List<SenseDto>?,
    val synonyms: List<String>?
)