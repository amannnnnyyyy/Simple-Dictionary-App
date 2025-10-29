package com.example.simpledictionary.presentation.words_history.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.example.simpledictionary.common.Resource
import com.example.simpledictionary.presentation.Screen
import com.example.simpledictionary.presentation.words_history.WordHistoryViewModel

@Composable
fun WordHistoryListScreen(
    navController: NavController,
    viewModel: WordHistoryViewModel = hiltViewModel(checkNotNull(LocalViewModelStoreOwner.current) {
                "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
            }, null)
) {
    var textFieldState by remember { mutableStateOf("") }
    viewModel.noSimilarDataNotifier.value = false

    val constrains = ConstraintSet{
        val input = createRefFor("input")
        val content = createRefFor("content")

        constrain(input){
            top.linkTo(parent.top, margin = 40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(content){
            top.linkTo(input.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
    }



    ConstraintLayout(constrains,modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .layoutId("content")
            .padding(20.dp)
        ){
            viewModel.stateForFiltering.let{ resource->
                if (textFieldState.isEmpty()){
                    GetAllWordDetails(viewModel, navController)
                    return@let
                }
                val res = resource.value
                when(res){
                    is Resource.Loading->{
                        GetAllWordDetails(viewModel, navController)
                    }
                    is Resource.Success->{
                        val data = res.data
                        data?.forEach { wordDetail ->
                            val language = wordDetail.entries?.find { it.language?.name!=null }?.language?.name
                            val word = wordDetail.word
                            if (word!=null && language!=null){
                                WordListItem(word, language, onClick = {
                                    navController.navigate(Screen.DetailScreen.route+"/${word}")
                                })
                            }
                        }
                    }
                    is Resource.Error ->{

                    }
                }
            }

        }
        Row(modifier = Modifier
            .layoutId("input")
            .clip(RoundedCornerShape(10.dp))
            .animateContentSize()
        ) {
            TextField(
                textFieldState,
                label = { Text("Search a word") },
                onValueChange = {
                    textFieldState = it
                    viewModel.filterWordDetailsByWord(textFieldState)
                     },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Spacer(modifier = Modifier.width(15.dp))
            if (viewModel.noSimilarDataNotifier.value){
                Button(onClick = {
                    navController.navigate(Screen.DetailScreen.route+"/${textFieldState}")
//                viewModel.getWordDetail(textFieldState)
                }) { Text("Search")}
            }
        }
    }

}


@Composable
fun GetAllWordDetails(viewModel: WordHistoryViewModel, navController: NavController){
    viewModel.state.value.let { resource ->
        when(resource){
            is Resource.Loading ->{}
            is Resource.Success ->{
                resource.data?.forEach { wordDetail ->
                    val language = wordDetail.entries?.find { it.language?.name!=null }?.language?.name
                    val word = wordDetail.word
                    if (word!=null && language!=null){
                        WordListItem(word, language){
                            navController.navigate(Screen.DetailScreen.route+"/${word}")
                        }
                    }
                }
            }
            is Resource.Error -> {}
        }
    }
}
