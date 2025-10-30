package com.example.simpledictionary.presentation.word_detail.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.presentation.word_detail.WordDetailItem
import com.example.simpledictionary.presentation.word_detail.WordDetailViewModel

@Composable
fun WordDetailScreen(
    word:String,
    viewModel: WordDetailViewModel = hiltViewModel(checkNotNull(LocalViewModelStoreOwner.current) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
}, null)) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(word) {
        viewModel.getWordDetailLocally(word)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

    when (val currentState = state) {
        is Resource.Error<*> -> {
            Text(
                text = currentState.message ?: "An unknown error occurred.",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(15.dp)
            )
        }

        is Resource.Loading<*> -> {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
        is Resource.Success<*> -> {
            Log.i("Recalled_detail", "WordDetailScreen: ${currentState.data?.word}")
            currentState.data?.let { wordDetail ->
                Log.i("Recalled_detail", "Called again: ${currentState.data.fromDb}")
                WordDetailItem(
                    data = wordDetail,
                    onSaveClicked = {
                        viewModel.addWordDetail(wordDetail)
                    },
                    onAnotherWordSearched = { word->
                        viewModel.getWordDetailLocally(word)
                    }
                )
            }
        }
    }
}
}

