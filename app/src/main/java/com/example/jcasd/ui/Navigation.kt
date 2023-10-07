package com.example.jcasd.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jcasd.data.local.ScreenEvent
import com.example.jcasd.data.local.UiState
import com.example.jcasd.ui.screens.AddScreen
import com.example.jcasd.ui.screens.DetailScreen
import com.example.jcasd.ui.screens.HomeScreen


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
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(Screen.Home.name) {
            HomeScreen(
                state = state,
                onEvent = onEvent,
                onNavigateToAdd = {
                    navController.navigate(Screen.Add.name)
                },
                onNavigateToDetail = {
                    navController.navigate(Screen.Detail.name)
                }
            )
        }
        composable(Screen.Add.name) {
            AddScreen(
                state = state,
                onEvent = onEvent,
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(Screen.Detail.name) {
            DetailScreen(
                state = state,
                onEvent = onEvent
            )
        }

    }
}