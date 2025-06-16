package com.example.mobilehome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobilehome.ui.navigation.screens.InventoryDetailScreen
import com.example.mobilehome.ui.navigation.screens.InventoryEntryScreen
import com.example.mobilehome.ui.navigation.screens.InventoryListScreen
import com.example.mobilehome.viewmodel.InventoryViewModel


@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: InventoryViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.InventoryList.route,
        modifier = modifier
    ) {
        composable(Screen.InventoryList.route) {
            InventoryListScreen(navController, viewModel)
        }
        composable(Screen.InventoryEntry.route) {
            InventoryEntryScreen(navController, viewModel)
        }
        composable(Screen.InventoryDetail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
            itemId?.let {
                InventoryDetailScreen(navController, it, viewModel)
            }
        }
    }
}

