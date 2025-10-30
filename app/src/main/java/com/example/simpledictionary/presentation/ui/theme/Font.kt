package com.example.simpledictionary.presentation.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.simpledictionary.R

val font_milven = FontFamily(
    Font(R.font.milven_regular, FontWeight.SemiBold)
)

val font_philosopher = FontFamily(
    Font(R.font.philosopher_bold, FontWeight.Bold),
    Font(R.font.philosopher_regular, FontWeight.Normal),
    Font(R.font.philosopher_italic, style = FontStyle.Italic),
    Font(R.font.philosopher_bold_italic, FontWeight.Bold, style = FontStyle.Italic)
)

