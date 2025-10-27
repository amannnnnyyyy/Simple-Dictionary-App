package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import com.example.simpledictionary.domain.model.WordDetail

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
fun WordDetailItem(){
val constraints  = ConstraintSet{
    val language = createRefFor("language")
    val pronunciations = createRefFor("pronunciations")
    val forms = createRefFor("forms")
    val synonyms = createRefFor("synonyms")
    val antonyms = createRefFor("antonyms")

}

    Box(modifier = Modifier.fillMaxSize().padding(20.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {

            }
            .padding(20.dp).background(Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text("Hello", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                overflow = TextOverflow.Ellipsis)
        }

    }
}