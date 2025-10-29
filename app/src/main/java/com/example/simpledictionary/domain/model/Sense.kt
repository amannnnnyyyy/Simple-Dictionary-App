package com.example.simpledictionary.domain.model

data class Sense(
    val antonyms: List<String>?=null,
    val definition: String?=null,
    val examples: List<String>?=null,
    val quotes: List<Quote>?=null,
    val subsenses: List<Sense>?=null,
    val synonyms: List<String>?=null,
    val tags: List<String>?=null
)
