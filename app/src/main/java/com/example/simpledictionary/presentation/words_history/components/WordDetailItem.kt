package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.Source
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.presentation.ui.common.ExpandableCard
import com.example.simpledictionary.presentation.word_detail.components.WordDetailContent
import com.example.simpledictionary.presentation.word_detail.components.WordDetailHeader
import com.example.simpledictionary.presentation.word_detail.components.source_card.SourceCard

@Preview()
@Composable
fun WordDetailItemMock(data: WordDetail = MOCK_DATA){
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }

    var expandedState by remember { mutableIntStateOf(0) }


val constraints  = ConstraintSet{
    val language = createRefFor("language")
    val pronunciations = createRefFor("pronunciations")
    val forms = createRefFor("forms")
    val senses = createRefFor("senses")
    val synonyms = createRefFor("synonyms")
    val antonyms = createRefFor("antonyms")

    constrain(language){
        top.linkTo(parent.top, margin = 5.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }

    constrain(pronunciations){
        top.linkTo(language.bottom, margin = 5.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }

    constrain(forms){
        top.linkTo(pronunciations.bottom, margin = 5.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }

    constrain(senses){
        top.linkTo(forms.bottom, margin = 5.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }
    constrain(synonyms){
        top.linkTo(senses.bottom, margin = 5.dp)
        bottom.linkTo(antonyms.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }
    constrain(antonyms){
        top.linkTo(synonyms.bottom, margin = 5.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.matchParent
    }

}

    val wholeConstraints = ConstraintSet{
        val word = createRefFor("word")
        val entries = createRefFor("entries")
        val source = createRefFor("source")

        constrain(word){
            top.linkTo(parent.top)
            bottom.linkTo(entries.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            verticalBias = 0f
        }
        constrain(entries){
            top.linkTo(word.bottom)
            bottom.linkTo(source.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            verticalBias = 0f
        }
        constrain(source){
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            verticalBias = 1f
        }
    }


    ConstraintLayout(wholeConstraints,
        modifier = Modifier
            .fillMaxSize()) {

            Box(Modifier.layoutId("word")){
                Text(data.word?:"", modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic)
            }
            Spacer(Modifier.height(10.dp))
            Box(modifier = Modifier.layoutId("entries")){
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    verticalArrangement = spacedBy(10.dp)
                ) {
                    itemsIndexed(data.entries?:listOf()){ index, item ->
                        ExpandableCard(index==expandedState, changeExpandedState = {
                            expandedState = if (expandedState==index) -1 else index
                        }, header = {
                            WordDetailHeader(index, expandedState){
                                expandedState = if (expandedState==index) -1 else index
                            }
                        }){
                            WordDetailContent(constraints,item)
                        }
                    }

                }
            }
            Box(modifier = Modifier.layoutId("source")){
                SourceCard(data.source?:Source(null, null))
            }
    }
}