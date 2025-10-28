package com.example.simpledictionary.presentation.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ExpandableCard(expandedState:Boolean, changeExpandedState:()-> Unit, header: @Composable ()->Unit, content:  @Composable ()->Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(900, easing = LinearOutSlowInEasing)
            ),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            changeExpandedState()
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            header()
            if(expandedState){
                content()
            }
        }
    }
}