package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Pronunciation

data class PronunciationDto(
    val tags: List<String>?,
    val text: String?,
    val type: String?
)

fun PronunciationDto.toPronunciation(): Pronunciation{
    return Pronunciation(
        tags = tags,
        text = text,
        type = type
    )
}