package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Form

data class FormDto(
    val tags: List<String>?,
    val word: String?
)

fun FormDto.toForm(): Form{
    return Form(
        tags = tags,
        word = word
    )
}