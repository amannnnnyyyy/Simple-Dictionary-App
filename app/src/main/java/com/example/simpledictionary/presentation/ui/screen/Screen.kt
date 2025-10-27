package com.example.simpledictionary.presentation.ui.screen

sealed class Screen(val route:String) {
    object MainScreen: Screen("MainScreen")
    object DetailScreen: Screen("DetailScreen")

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg->
            append("/$arg")
        }
    }
}