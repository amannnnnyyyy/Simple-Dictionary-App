package com.example.simpledictionary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simpledictionary.presentation.Screen
import com.example.simpledictionary.presentation.word_detail.components.WordDetailScreen
import com.example.simpledictionary.presentation.words_history.components.WordHistoryListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            WordHistoryListScreen(navController)
        }

        composable(route = Screen.DetailScreen.route+"/{word}",
            arguments = listOf(
                navArgument("word"){
                    type = NavType.StringType
                    defaultValue = "hello"
                    nullable = true
                })) { backEntry->
            backEntry.arguments?.getString("word")?.let {
                WordDetailScreen(it)
            }
        }
    }
}