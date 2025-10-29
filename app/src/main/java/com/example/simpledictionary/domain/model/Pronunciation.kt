package com.example.simpledictionary.domain.model

data class Pronunciation(
    val tags: List<String>?=null,
    val text: String?=null,
    val type: String?=null
)
