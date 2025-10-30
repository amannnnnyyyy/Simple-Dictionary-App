package com.example.simpledictionary.presentation.word_detail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.simpledictionary.R
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.Source
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.presentation.ui.common.ExpandableCard
import com.example.simpledictionary.presentation.word_detail.components.WordDetailContent
import com.example.simpledictionary.presentation.word_detail.components.WordDetailHeader
import com.example.simpledictionary.presentation.word_detail.components.source_card.SourceCard
import kotlin.math.roundToInt

@Preview()
@Composable
fun WordDetailItemMock(data: WordDetail = MOCK_DATA, onSaveClicked:()-> Unit={}, onAnotherWordSearched:(String)-> Unit={}){
    val scrollState = rememberScrollState()
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    var expandedState by remember { mutableIntStateOf(0) }
    var savedWordDetail by remember { mutableStateOf(false) }


    Log.i("Recalled_detail", "outside of inside: ${data.fromDb}")



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
            verticalBias = 0.2f
        }
        constrain(entries){
            top.linkTo(word.bottom, margin = 10.dp)
//            bottom.linkTo(source.top)
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
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .layoutId("word")
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(15.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(data.word?:"",
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(2f),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic)
                Log.i("Recalled_detail", "WordDetailItemMock: ${data.fromDb}")
                Icon(
                    painterResource(if (savedWordDetail || (data.fromDb)?:false) R.drawable.saved else R.drawable.unsaved),
                    "Saved Status Indicator",
                    modifier = Modifier
                        .clickable((data.fromDb?.let { !it }) ?: true) {
                            onSaveClicked()
                            savedWordDetail = true
                        }
                        .height(20.dp)
                        .width(20.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
            }
            Box(modifier = Modifier.layoutId("entries")){
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(if (isPortrait) 0.89f else 0.7f),
                    verticalArrangement = spacedBy(10.dp)
                ) {
                    itemsIndexed(data.entries?:listOf()){ index, item ->
                        ExpandableCard(index==expandedState, changeExpandedState = {
                            expandedState = if (expandedState==index) -1 else index
                        }, header = {
                            WordDetailHeader(index, word = data.word?:"", meaning = item.senses?.getOrNull(0)?.definition?:"", expandedState){
                                expandedState = if (expandedState==index) -1 else index
                            }
                        }){
                            WordDetailContent(constraints,item,onAnotherWordSearched={ word->
                                onAnotherWordSearched(word)
                            })
                        }
                    }

                }
            }
            Box(modifier = Modifier
                .layoutId("source")
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                )
            ){
                SourceCard(data.source?:Source(null, null))
            }


    }
}