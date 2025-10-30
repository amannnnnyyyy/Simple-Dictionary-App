package com.example.simpledictionary.presentation.word_detail.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
}, null)){
    var showLoading by remember { mutableStateOf(true) }
    var showFinalLoading by remember { mutableStateOf(true) }


    val scope = rememberCoroutineScope()
    var currentProgress by remember { mutableFloatStateOf(0f) }


    LaunchedEffect(Unit) {
        scope.launch {
            fetchWordDetail(word, viewModel,
                changeShowLoading = {
                showLoading=it
            },
                changeFinalShowLoading = {
                showFinalLoading = it
            },
                updateProgress = {
                    currentProgress = it
                })
        }
    }

    if (showLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }else if (showFinalLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }else{
        viewModel.state.value.wordDetails.let{ wordDetail ->
            WordDetailItemMock(wordDetail, onSaveClicked = {viewModel.addWordDetail(wordDetail)})
        }

    }
}

private suspend fun fetchWordDetail(
    word:String,
    viewModel: WordDetailViewModel,
    changeShowLoading:(Boolean)-> Unit,
    changeFinalShowLoading: (Boolean)-> Unit,
    updateProgress: (Float) -> Unit
    ){
    handleWordCollect(word, viewModel, doneFirstLoading = { value->
        if (value){
           changeShowLoading(false)
        }
    }){ progress ->
        updateProgress(progress)
    }
    changeFinalShowLoading(false)
}


private fun handleWordCollect(word:String, viewModel: WordDetailViewModel, doneFirstLoading:(Boolean)-> Unit, updateProgress: (Float) -> Unit){
    for (x in 0..100){
        updateProgress(x.toFloat()/100f)
    }
    doneFirstLoading(true)
    runBlocking {
        val first = async { viewModel.getWordDetailLocally(word) }

        val res = first.await()

        Log.i("choosingSource", "handleWordCollect: ${res.data}")
        //viewModel.getWordDetail(word)
        //Log.i("choosingSource", "handleWordCollect: ${viewModel.state}")
    }

}

