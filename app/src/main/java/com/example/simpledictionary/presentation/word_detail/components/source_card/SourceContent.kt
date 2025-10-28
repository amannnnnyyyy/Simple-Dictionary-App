package com.example.simpledictionary.presentation.word_detail.components.source_card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.simpledictionary.domain.model.Source

@Composable
fun SourceContent(source: Source) {
    Column {
        Text("Source", modifier = Modifier.padding(5.dp).fillMaxWidth(0.6f), style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis)
        Text(source.url?:"", modifier = Modifier.border(2.dp, Color.LightGray, CircleShape).padding(5.dp).fillMaxWidth(), style = MaterialTheme.typography.bodyMedium,
            color = Color.White, textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(10.dp))
        Text("License", modifier = Modifier.padding(5.dp).fillMaxWidth(0.6f), style = MaterialTheme.typography.bodyMedium,
            color = Color.White,textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis)
        FlowRow(modifier = Modifier.fillMaxWidth().border(2.dp, Color.LightGray,
            CircleShape
        ), horizontalArrangement = Arrangement.Center) {
            Text(source.license?.name?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                overflow = TextOverflow.Ellipsis)
            Text(source.license?.url?:"", modifier = Modifier.padding(5.dp), style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                overflow = TextOverflow.Ellipsis)
        }
    }
}