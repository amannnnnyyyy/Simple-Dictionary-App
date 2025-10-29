package com.example.simpledictionary.presentation.word_detail.components.source_card

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.Source
import com.example.simpledictionary.presentation.ui.common.ExpandableCard


@Preview()
@Composable
fun SourceCard(source: Source= MOCK_DATA.source?:Source(null, null)) {
    var expandedState by remember { mutableStateOf(false) }
    var showTextAnimated by remember { mutableStateOf(true) }

    Card(
        modifier = if (expandedState)
            Modifier
                .fillMaxWidth()
                .animateContentSize()
            else Modifier
                .animateContentSize()
    ) {
        Column(
            modifier = if (expandedState)
                Modifier.fillMaxWidth()
                    .padding(12.dp)
                    else
                Modifier
                    .padding(0.dp)
                .animateContentSize()
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
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