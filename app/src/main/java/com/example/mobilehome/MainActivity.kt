package com.example.mobilehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.mobilehome.data.AppDatabase
import com.example.mobilehome.data.InventoryRepository
import com.example.mobilehome.ui.navigation.AppNavHost
import com.example.mobilehome.ui.theme.MobileHomeTheme
import com.example.mobilehome.viewmodel.InventoryViewModel
import com.example.mobilehome.viewmodel.InventoryViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(applicationContext)
        val repository = InventoryRepository(db.inventoryDao())
        val viewModelFactory = InventoryViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[InventoryViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            MobileHomeTheme {
                AppNavHost(navController = navController, viewModel = viewModel)
            }
        }
    }
}
