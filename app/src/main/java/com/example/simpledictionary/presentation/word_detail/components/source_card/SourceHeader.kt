package com.example.simpledictionary.presentation.word_detail.components.source_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.simpledictionary.R

@Composable
fun SourceHeader(showTextAnimated:Boolean, expandedState:Boolean, changeAnimatedTextStatus:()-> Unit, changeExpandedState:()-> Unit) {
    val rotationState by animateFloatAsState(
        targetValue = if ((expandedState)) 0f else 180f
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        IconButton(modifier = Modifier.wrapContentWidth(),onClick = {
            changeAnimatedTextStatus()
            changeExpandedState()
        }) {
            Icon(
                painter = painterResource(id =R.drawable.down_arrow),
                contentDescription = "arrow",
                modifier = Modifier.rotate(rotationState),
            )
        }
        AnimatedVisibility(visible = showTextAnimated,
            enter = slideInHorizontally() + fadeIn(animationSpec = tween(durationMillis = 500, easing = LinearEasing)),
            exit = slideOutHorizontally() + fadeOut(animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing))
        ) {
            Text(
                "Source",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}