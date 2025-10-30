package com.example.simpledictionary.presentation.word_detail.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.domain.model.WordDetail
import com.example.simpledictionary.presentation.word_detail.WordDetailViewModel
import com.example.simpledictionary.presentation.word_detail.WordDetailItemMock
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import kotlin.math.log

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
                modifier = Modifier.align(Alignment.Center).padding(15.dp)
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
            currentState.data?.let { wordDetail ->
                WordDetailItemMock(
                    data = wordDetail,
                    onSaveClicked = { viewModel.addWordDetail(wordDetail) }
                )
            }
        }
    }
}
}

