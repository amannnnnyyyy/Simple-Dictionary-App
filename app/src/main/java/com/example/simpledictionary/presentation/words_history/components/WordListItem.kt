package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview()
@Composable
fun WordListItem(word:String = "",description:String="") {
    Box(modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(10.dp)).background(
        Color.DarkGray).shadow(2.dp, RoundedCornerShape(10.dp), spotColor = Color.Cyan).padding(15.dp)){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize().padding(5.dp)) {
            Text(word, color = Color.White, fontSize = 22.sp, modifier = Modifier.weight(1f).padding(5.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(description, color = Color.White, fontSize = 22.sp, modifier = Modifier.weight(1f).padding(5.dp), maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}