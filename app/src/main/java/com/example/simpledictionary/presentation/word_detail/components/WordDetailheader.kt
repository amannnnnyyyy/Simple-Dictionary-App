package com.example.simpledictionary.presentation.word_detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun WordDetailHeader(index: Int, expandedState: Int, changeExpandedState:()->Unit) {
    val rotationState by animateFloatAsState(
        targetValue = if((expandedState!=-1)) 90f else 0f
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Word Detail ${index+1}",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.weight(6f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
        IconButton(modifier =
            Modifier.alpha(0.2f)
                .weight(1f)
                .rotate(rotationState)
            , onClick = {
                changeExpandedState()
            }) {
            Text("<", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp, modifier = Modifier.weight(6f), color = Color.White)
        }
    }
}