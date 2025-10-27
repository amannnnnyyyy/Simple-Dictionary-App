package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.presentation.ui.common.ExpandableCard

@Composable
fun WordDetailItem(
    wordDetail: WordDetail,
    onItemClick:(WordDetail)-> Unit) {
    val meaning = wordDetail.entries?.map { detail->
        detail.synonyms
    }.toString()

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(wordDetail)
        }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Text("${wordDetail.word} / ${meaning}", style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis)
    }
}


@Preview()
@Composable
fun WordDetailItemMock(data: WordDetail = MOCK_DATA, modifier: Modifier= Modifier){
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }


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
            top.linkTo(entries.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }


    ConstraintLayout(wholeConstraints,
        modifier = Modifier
            .fillMaxWidth()) {

            Box(Modifier.layoutId("word")){
                Text(data.word?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis)
            }
            Spacer(Modifier.height(10.dp))
            Box(modifier = Modifier.layoutId("entries")){
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    verticalArrangement = spacedBy(10.dp)
                ) {
                    itemsIndexed(data.entries?:listOf()){ index, item ->
                        ExpandableCard(index,item,constraints)
                    }

                }
            }
            Column(modifier = Modifier.layoutId("source")) {
                Text("Source", modifier = Modifier.padding(5.dp).fillMaxWidth(0.6f), style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis)
                Text(data.source?.url?:"", modifier = Modifier.border(2.dp, Color.LightGray, CircleShape).padding(5.dp).fillMaxWidth(), style = MaterialTheme.typography.bodyMedium,
                    color = Color.White, textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(10.dp))
                Text("License", modifier = Modifier.padding(5.dp).fillMaxWidth(0.6f), style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis)
                FlowRow(modifier = Modifier.fillMaxWidth().border(2.dp, Color.LightGray,
                    CircleShape
                ), horizontalArrangement = Arrangement.Center) {
                    Text(data.source?.license?.name?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis)
                    Text(data.source?.license?.url?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis)
                }
            }

    }
}