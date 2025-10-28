package com.example.simpledictionary.presentation.word_detail.components.source_card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpledictionary.R
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.Source


@Preview()
@Composable
fun SourceCard(source: Source= MOCK_DATA.source?:Source(null, null)) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if ((expandedState)) 0f else 180f
    )

    var showTextAnimated by remember { mutableStateOf(true) }




    Card(
        modifier = Modifier.fillMaxWidth()
            .animateContentSize()
    ) {
        IconButton(modifier = Modifier.fillMaxWidth(),onClick = {
            showTextAnimated = !showTextAnimated
            expandedState = !expandedState
        }) {
            Icon(
                painter = painterResource(id =R.drawable.down_arrow),
                contentDescription = "arrow",
                modifier = Modifier.rotate(rotationState),
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp).animateContentSize()) {
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
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
            if (expandedState){
                SourceContent(source = source)
            }
        }
    }
}