package com.example.simpledictionary.data.remote.dto

data class Sense(
    val antonyms: List<String>?,
    val definition: String?,
    val examples: List<String>?,
    val quotes: List<Quote>?,
    val subsenses: List<String>?,
    val synonyms: List<String>?,
    val tags: List<String>?
)