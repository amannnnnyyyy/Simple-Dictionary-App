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
import com.example.simpledictionary.presentation.ui.common.ExpandableCard


@Preview()
@Composable
fun SourceCard(source: Source= MOCK_DATA.source?:Source(null, null)) {
    var expandedState by remember { mutableStateOf(false) }
    var showTextAnimated by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth()
            .animateContentSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp).animateContentSize()) {
            ExpandableCard(
                expandedState,
                changeExpandedState = {
                    expandedState = !expandedState
                },
                header = {
                    SourceHeader(
                        showTextAnimated,
                        expandedState,
                        changeAnimatedTextStatus = {
                            showTextAnimated = !showTextAnimated
                        }){
                        expandedState = !expandedState
                    }}) {
                SourceContent(source)
            }
        }
    }
}