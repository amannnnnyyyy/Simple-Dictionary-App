package com.example.simpledictionary.data.remote.dto

import com.example.simpledictionary.domain.model.License

data class LicenseDto(
    val name: String?,
    val url: String?
)

fun LicenseDto.toLicense() = License(name, url)