@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.mobilehome.ui.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilehome.data.InventoryEntity
import com.example.mobilehome.viewmodel.InventoryViewModel

@Composable
fun InventoryEntryScreen(
    navController: NavController,
    viewModel: InventoryViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var warranty by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    val isValid = name.isNotBlank() && category.isNotBlank() && quantity.toIntOrNull() != null && price.toDoubleOrNull() != null

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Inventory Item") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it; showError = false }, isError = showError && name.isBlank(), label = { Text("Name") })
            OutlinedTextField(value = category, onValueChange = { category = it; showError = false }, isError = showError && category.isBlank(), label = { Text("Category") })
            OutlinedTextField(value = quantity, onValueChange = { quantity = it; showError = false }, isError = showError && quantity.toIntOrNull() == null, label = { Text("Quantity") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            OutlinedTextField(value = price, onValueChange = { price = it; showError = false }, isError = showError && price.toDoubleOrNull() == null, label = { Text("Price") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Purchase Date") })
            OutlinedTextField(value = warranty, onValueChange = { warranty = it }, label = { Text("Warranty Months") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            OutlinedTextField(value = expiration, onValueChange = { expiration = it }, label = { Text("Expiration Date") })

            if (showError && !isValid) {
                Text("Please fill out required fields correctly.", color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (!isValid) {
                        showError = true
                        return@Button
                    }

                    val item = InventoryEntity(
                        name = name,
                        category = category,
                        quantity = quantity.toIntOrNull() ?: 0,
                        purchasePrice = price.toDoubleOrNull() ?: 0.0,
                        purchaseDate = date,
                        warrantyMonths = warranty.toIntOrNull() ?: 0,
                        expirationDate = expiration
                    )

                    viewModel.addItem(item)

                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}
