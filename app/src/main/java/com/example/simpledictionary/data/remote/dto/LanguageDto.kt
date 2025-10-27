package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Language

data class LanguageDto(
    val code: String?,
    val name: String?
)


fun LanguageDto.toLanguage() = Language(code, name)