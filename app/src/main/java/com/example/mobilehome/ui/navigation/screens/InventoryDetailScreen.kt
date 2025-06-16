package com.example.mobilehome.ui.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilehome.data.AppDatabase
import com.example.mobilehome.data.InventoryRepository
import com.example.mobilehome.viewmodel.InventoryViewModel
import com.example.mobilehome.viewmodel.InventoryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryDetailScreen(
    navController: NavController,
    itemId: Int,
    repository: InventoryViewModel
) {
    val context = LocalContext.current
    val owner = LocalViewModelStoreOwner.current

    // Create Repository
    val repository = remember {
        val db = AppDatabase.getInstance(context)
        InventoryRepository(db.inventoryDao())
    }

    val viewModel: InventoryViewModel = viewModel(
        viewModelStoreOwner = owner!!,
        factory = InventoryViewModelFactory(repository)
    )

    val item by viewModel.getItemById(itemId).collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Item Details") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (item == null) {
                Text("Item not found", style = MaterialTheme.typography.bodyLarge)
            } else {
                item?.let {
                    Text("Name: ${it.name}", style = MaterialTheme.typography.headlineSmall)
                    Text("Category: ${it.category}")
                    Text("Quantity: ${it.quantity}")
                    Text("Price: \$${it.purchasePrice}")
                    Text("Purchase Date: ${it.purchaseDate}")
                    Text("Warranty: ${it.warrantyMonths} months")
                    it.expirationDate?.let { date ->
                        Text("Expires: $date")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            navController.navigate("entry/${it.id}")
                        }) {
                            Text("Edit")
                        }

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            onClick = {
                                viewModel.deleteItem(it)
                                navController.popBackStack()
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
