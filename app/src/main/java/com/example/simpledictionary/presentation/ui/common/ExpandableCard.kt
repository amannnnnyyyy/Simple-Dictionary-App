package com.example.simpledictionary.presentation.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.example.simpledictionary.common.Constants.MOCK_DATA
import com.example.simpledictionary.domain.model.Entry


@Composable
fun ExpandableCard(index: Int = 0, item: Entry= MOCK_DATA.entries?.get(0)?: Entry(null, null, null, null, null, null, null), constraints: ConstraintSet) {
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
                ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.background(color = Color.Blue).layoutId("language")){
                        FlowRow(modifier = Modifier.fillMaxWidth()) {
                            Text("Language", modifier = Modifier.padding(5.dp).fillMaxWidth(0.3f), style = MaterialTheme.typography.bodyMedium,
                                color = Color.White, textAlign = TextAlign.Start,
                                overflow = TextOverflow.Ellipsis)
                            Text(item.language?.name?:"", modifier = Modifier.padding(5.dp).fillMaxWidth(0.3f), style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis)
                            Text(item.partOfSpeech?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis)
                        }
                    }
                    Box(modifier = Modifier.background(color = Color.Transparent).layoutId("pronunciations")){
                        Column(modifier = Modifier.padding(5.dp).fillMaxWidth()) {
                            //items(item.pronunciations?:listOf()){ pronunciation ->
                            Row {
                                item.pronunciations?.size?.let {
                                    Text(if (it>0) item.pronunciations[0].type?:"" else "")
                                    Spacer(Modifier.width(5.dp))
                                    Text(if (it>0) item.pronunciations[0].text?:"" else "")
                                    Spacer(Modifier.width(5.dp))
                                    FlowRow {
                                        if (it>0){
                                            for (tag in item.pronunciations[0].tags?:listOf()){
                                                Text(tag)
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(5.dp))
                            // }
                        }
                    }
                    Box(modifier = Modifier.background(color = Color.Transparent).layoutId("forms")){
                        val forms = item.forms
                        FlowRow(verticalArrangement = spacedBy(5.dp)) {
                            for (form in forms?:listOf()){
                                Text(form.word?:"", modifier = Modifier.border(1.dp, Color.DarkGray,
                                    RoundedCornerShape(10.dp)
                                ).padding(3.dp))
                                Spacer(Modifier.width(5.dp))
                                Spacer(Modifier.height(5.dp))
                            }
                        }
                    }
                    Column(modifier = Modifier.layoutId("senses")){
                        val senses = item.senses
                        for (sense in senses?:listOf()){
                            Text("meaning", color = Color.White, fontStyle = FontStyle.Italic, modifier = Modifier.padding(3.dp))
                            Spacer(Modifier.height(3.dp))
                            Column(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color.DarkGray).padding(5.dp)){
                                Text(sense.definition?:"",
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp)).background(color = Color.LightGray)
                                        .padding(5.dp),
                                    color = Color.Black,
                                    textAlign = TextAlign.Justify
                                )
                                if (((sense.examples?.size ?: 0) > 0)){
                                    Spacer(Modifier.height(3.dp))
                                    Text("example", color = Color.White, fontStyle = FontStyle.Italic, modifier = Modifier.padding(3.dp))
                                    Spacer(Modifier.height(3.dp))
                                    Column(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(Color.Black).fillMaxWidth().padding(5.dp)){
                                        for (example in sense.examples?:listOf()){
                                            Spacer(Modifier.height(2.dp))
                                            FlowRow {
                                                Text(example,
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(5.dp)).background(color = Color.Transparent),
                                                    color = Color.White,
                                                    textAlign = TextAlign.Justify
                                                )
                                            }
                                        }
                                    }
                                }

                                if (((sense.quotes?.size ?: 0) > 0)){
                                    Spacer(Modifier.height(3.dp))
                                    Text("quotes", color = Color.White, fontStyle = FontStyle.Italic, modifier = Modifier.padding(3.dp))
                                    Spacer(Modifier.height(3.dp))
                                    Column(modifier = Modifier.clip(RoundedCornerShape(4.dp)).fillMaxWidth().padding(5.dp)){
                                        for (quote in sense.quotes?:listOf()){
                                            Spacer(Modifier.height(2.dp))
                                            Column(modifier = Modifier.background(Color.Black).padding(10.dp)) {
                                                quote.text?.let { text->
                                                    FlowRow {
                                                        Text(text,
                                                            modifier = Modifier
                                                                .clip(RoundedCornerShape(5.dp)).background(color = Color.Transparent),
                                                            color = Color.White,
                                                            textAlign = TextAlign.Justify
                                                        )
                                                    }
                                                    quote.reference?.let { ref->
                                                        FlowRow {
                                                            Text("reference",color = Color.Green)
                                                            Text(ref,
                                                                modifier = Modifier
                                                                    .clip(RoundedCornerShape(5.dp)).background(color = Color.DarkGray).padding(5.dp),
                                                                color = Color.White,
                                                                textAlign = TextAlign.Justify
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.height(10.dp))

                        }
                    }
                    Column(modifier = Modifier.background(color = Color.Transparent).layoutId("synonyms")){
                        val synonyms = item.synonyms
                        if ((synonyms?.size?:0)>0){
                            Text("Synonyms")
                            FlowRow  {
                                for(syn in synonyms?:listOf()){
                                    Text(syn, Modifier.clip(RoundedCornerShape(15.dp)).padding(2.dp).background(Color.LightGray).padding(2.dp), color = Color.Black)
                                    Spacer(Modifier.width(5.dp))
                                }
                            }
                        }
                    }
                    Column(modifier = Modifier.background(color = Color.Transparent).layoutId("antonyms")){
                        val antonyms = item.antonyms
                        if ((antonyms?.size?:0)>0){
                            Text("Antonyms")
                            FlowRow {
                                for(ant in antonyms?:listOf()){
                                    Text(ant, color = Color.Black,modifier = Modifier.clip(RoundedCornerShape(15.dp)).padding(2.dp).background(Color.LightGray).padding(2.dp))
                                    Spacer(Modifier.width(5.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}