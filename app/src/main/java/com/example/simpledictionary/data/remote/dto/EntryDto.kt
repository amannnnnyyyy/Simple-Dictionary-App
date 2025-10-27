package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Entry
import com.example.simpledictionary.domain.model.Form
import com.example.simpledictionary.domain.model.Pronunciation
import com.example.simpledictionary.domain.model.Sense

data class EntryDto(
    val antonyms: List<String>?,
    val forms: List<FormDto>?,
    val language: LanguageDto?,
    val partOfSpeech: String?,
    val pronunciations: List<PronunciationDto>?,
    val senses: List<SenseDto>?,
    val synonyms: List<String>?
)

fun EntryDto.toEntry(): Entry{
    val formList = mutableListOf<Form>()
    forms?.forEach { formDto ->
        formList.add(formDto.toForm())
    }

    val pronunciationList = mutableListOf<Pronunciation>()
    pronunciations?.forEach { pronunciationDto ->
        pronunciationList.add(pronunciationDto.toPronunciation())
    }

    val senseList = mutableListOf<Sense>()
    senses?.forEach { senseDto ->
        senseList.add(senseDto.toSense())
    }

    return Entry(
        language = language?.toLanguage(),
        antonyms = antonyms,
        forms = formList,
        partOfSpeech = partOfSpeech,
        pronunciations = pronunciationList,
        senses = senseList,
        synonyms = synonyms
    )
}