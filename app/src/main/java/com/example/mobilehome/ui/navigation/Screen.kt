package com.example.mobilehome.ui.navigation

sealed class Screen(val route: String) {
    object InventoryList : Screen("list")
    object InventoryEntry : Screen("entry")
    object InventoryDetail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}
