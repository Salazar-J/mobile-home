package com.example.mobilehome.ui.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilehome.ui.navigation.Screen
import com.example.mobilehome.viewmodel.InventoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryListScreen(
    navController: NavController,
    viewModel: InventoryViewModel
) {
    val items by viewModel.allItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mobile Home Inventory") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.InventoryEntry.route)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Item")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (items.isEmpty()) {
                Text("No items yet. Start adding!", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(items) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            onClick = {
                                navController.navigate(Screen.InventoryDetail.createRoute(item.id))
                            }
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(item.name, style = MaterialTheme.typography.titleMedium)
                                Text("Qty: ${item.quantity} | ${item.category}")
                            }
                        }
                    }
                }
            }
        }
    }
}
