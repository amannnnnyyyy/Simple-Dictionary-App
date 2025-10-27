package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.example.simpledictionary.presentation.word_detail.WordDetailViewModel

@Composable
fun WordHistoryListScreen(
    navController: NavController,
    viewModel: WordDetailViewModel = hiltViewModel(checkNotNull(LocalViewModelStoreOwner.current) {
                "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
            }, null)
) {
    var textFieldState by remember { mutableStateOf("") }
    val state = viewModel.state.value
    val modifier = Modifier
    Column (modifier = modifier.fillMaxSize().padding(20.dp)){
       Row {
           TextField(
               textFieldState,
               label = { Text("Search a word") },
               onValueChange = { textFieldState = it },
               singleLine = true,
               modifier = Modifier.fillMaxWidth(0.6f)
           )
           Spacer(modifier = Modifier.width(15.dp))
           Button(onClick = {
              viewModel.getWordDetail(textFieldState)
           }) { Text("Search")}
       }
        Spacer(modifier = Modifier.height(15.dp))

       // LazyColumn(modifier = Modifier.fillMaxSize()) {
        WordDetailItemMock( viewModel.state.value.wordDetails,modifier = modifier)
     //   }
    }
}