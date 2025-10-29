package com.example.simpledictionary.presentation.word_detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordDetailHeader(index: Int,word:String, meaning:String, expandedState: Int, changeExpandedState:()->Unit) {
    val rotationState by animateFloatAsState(
        targetValue = if((expandedState!=index)) 90f else 0f
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(Color.White, fontSize = 20.sp)){
                val firstChar:String = word.getOrNull(0)?.toString()?:word
                append(firstChar)
            }
            if (word.length>1){
                withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)){
                    append(word.substring(1))
                }
            }
            withStyle(SpanStyle(Color.White, baselineShift = BaselineShift.Subscript, fontSize = 10.sp)){
                append((index+1).toString())
            }
        },
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = meaning,
            fontWeight = FontWeight.Thin,
            modifier = Modifier.weight(6f).padding(start = 8.dp, end = 5.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(modifier =
            Modifier.alpha(0.2f)
                .rotate(rotationState)
            , onClick = {
                changeExpandedState()
            }) {
            Text("<", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp, modifier = Modifier.weight(6f), color = Color.White)
        }
    }
}