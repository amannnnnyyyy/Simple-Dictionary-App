package com.example.simpledictionary.presentation.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ExpandableCard(index: Int = 0, content:  @Composable ()->Unit) {
    var expandedState by remember { mutableIntStateOf(if (index==0)0 else -1) }
    val rotationState by animateFloatAsState(
        targetValue = if((expandedState!=-1)) 90f else 0f
    )

    Card(
        modifier = Modifier.fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(900, easing = LinearOutSlowInEasing)
            ),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            expandedState = if (expandedState==index) -1 else index
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
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
                    expandedState = if (expandedState==index) -1 else index
                }) {
                    Text("<", fontWeight = FontWeight.ExtraBold, fontSize = 25.sp, modifier = Modifier.weight(6f), color = Color.White)
                }
            }
            if(expandedState==index){
                content()
            }
        }
    }
}