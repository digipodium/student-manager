package com.example.jcasd.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.UiState


enum class Screen {
    Home,
    Add,
    Detail
}

@Composable
fun Navigation(
    state: UiState,
    onEvent: (ScreenEvent) -> Unit,
    navController: NavHostController = rememberNavController()
) {

}