package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.Source

data class SourceDto(
    val license: LicenseDto?,
    val url: String?
)

fun SourceDto.toSource() = Source(license?.toLicense(), url)