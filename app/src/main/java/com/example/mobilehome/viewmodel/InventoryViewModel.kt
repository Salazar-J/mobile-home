package com.example.mobilehome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilehome.data.InventoryEntity
import com.example.mobilehome.data.InventoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    val allItems: StateFlow<List<InventoryEntity>> = repository.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getItemsByCategory(category: String): StateFlow<List<InventoryEntity>> =
        repository.getItemsByCategory(category)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun searchItems(keyword: String): StateFlow<List<InventoryEntity>> =
        repository.searchItems(keyword)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getItemById(id: Int): StateFlow<InventoryEntity?> =
        repository.getItemById(id)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun addItem(item: InventoryEntity) {
        viewModelScope.launch { repository.insertItem(item) }
    }

    fun updateItem(item: InventoryEntity) {
        viewModelScope.launch { repository.updateItem(item) }
    }

    fun deleteItem(item: InventoryEntity) {
        viewModelScope.launch { repository.deleteItem(item) }
    }
}
