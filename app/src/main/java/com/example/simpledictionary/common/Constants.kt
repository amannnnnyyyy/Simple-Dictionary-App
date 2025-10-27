package com.example.simpledictionary.common

import com.example.simpledictionary.domain.model.Entry
import com.example.simpledictionary.domain.model.Form
import com.example.simpledictionary.domain.model.Language
import com.example.simpledictionary.domain.model.License
import com.example.simpledictionary.domain.model.Pronunciation
import com.example.simpledictionary.domain.model.Quote
import com.example.simpledictionary.domain.model.Sense
import com.example.simpledictionary.domain.model.Source
import com.example.simpledictionary.domain.model.WordDetail

object Constants {
    const val BASE_URL = "https://freedictionaryapi.com"

    val MOCK_DATA = WordDetail(
        word = "there",
        entries = listOf(
            Entry(
                language = Language(code = "en", name = "English"),
                partOfSpeech = "Adverb",
                pronunciations = listOf(
                    Pronunciation(
                        type = "ipa",
                        text = "/ðɛː/",
                        tags = listOf("Received Pronunciation")
                    ),
                    Pronunciation(
                        type = "ipa",
                        text = "/ðɛɚ/",
                        tags = listOf("US")
                    )
                ),
                forms = listOf(
                    Form(
                        word = "dar",
                        tags = listOf(
                            "alternative"
                        )
                    ),
                    Form(
                        word = "dere",
                        tags = listOf(
                            "alternative"
                        )
                    )
                ),
                senses = listOf(
                    Sense(
                        definition = "(location) In or at a place or location (stated, implied or otherwise indicated) that is perceived to be away from, or at a relative distance from, the speaker (compare here).",
                        tags = listOf("not comparable"),
                        examples = listOf(
                            "I know Bristol quite well as I used to live there.",
                            "I looked in the cupboard and my keys were there!",
                            "The air there is beneficial to health.",
                            "I consulted Wikipedia, and it says there that he died in 1970.",
                            "The view up here is better than the view down there!",
                            "There's that book I've been looking for!",
                            "1769, King James Bible, Oxford Standard text, Genesis, 2, viii,\nThe Lord God planted a garden eastward in Eden; and there he put the man whom he had formed."
                        ),
                        quotes = listOf(
                            Quote(
                                text = "And in a dark and dankish vault at home / There left me and my man, both bound together;",
                                reference = "c. 1594 (date written), William Shakespeare, “The Comedie of Errors”, in Mr. William Shakespeares Comedies, Histories, & Tragedies […] (First Folio), London: […] Isaac Iaggard, and Ed[ward] Blount, published 1623, →OCLC, [Act V, scene i]:"
                            ),
                            Quote(
                                text = "To veil the heav'n, tho' darkneſs there might well / Seem twilight here.",
                                reference = "1667, John Milton, “(please specify the page number)”, in Paradise Lost. […], London: […] [Samuel Simmons], and are to be sold by Peter Parker […]; [a]nd by Robert Boulter […]; [a]nd Matthias Walker, […], →OCLC; republished as Paradise Lost in Ten Books: […], London: Basil Montagu Pickering […], 1873, →OCLC:"
                            )
                        ),
                        synonyms = listOf<String>(),
                        antonyms = listOf<String>(),
                        subsenses = listOf(
                            Sense(
                                definition = "(figuratively) At that point, stage, etc., visualised as a distinct place.",
                                antonyms = listOf(),
                                examples = listOf(
                                    "He did not stop there, but continued his speech.",
                                    "They patched up their differences, but matters did not end there."
                                ),
                                quotes = listOf(
                                    Quote(
                                        text = "The law, that threaten’d death, becomes thy friend / And turns it to exile; there art thou happy.",
                                        reference = "c. 1591–1595 (date written), William Shakespeare, “The Tragedie of Romeo and Ivliet”, in Mr. William Shakespeares Comedies, Histories, & Tragedies […] (First Folio), London: […] Isaac Iaggard, and Ed[ward] Blount, published 1623, →OCLC, [Act III, scene iii]:"
                                    )
                                ),
                                subsenses = listOf(),
                                synonyms = listOf(),
                                tags = listOf(),
                            )
                        )
                    )
                ),
                antonyms = listOf(),
                synonyms = listOf()
            )
        ),
        source = Source(
            url = "https://en.wiktionary.org/wiki/there",
            license = License(
                name = "CC BY-SA 4.0",
                url = "https://creativecommons.org/licenses/by-sa/4.0/"
            )
        )
    )
}