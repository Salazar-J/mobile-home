package com.example.mobilehome.data

import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val dao: InventoryDao) {

    fun getAllItems(): Flow<List<InventoryEntity>> = dao.getAllItemsFlow()

    fun getItemsByCategory(category: String): Flow<List<InventoryEntity>> =
        dao.getItemsByCategoryFlow(category)

    fun searchItems(keyword: String): Flow<List<InventoryEntity>> =
        dao.searchItemsFlow(keyword)

    fun getItemById(id: Int): Flow<InventoryEntity?> =
        dao.getItemByIdFlow(id)

    suspend fun insertItem(item: InventoryEntity) {
        dao.insertItem(item)
    }

    suspend fun updateItem(item: InventoryEntity) {
        dao.updateItem(item)
    }

    suspend fun deleteItem(item: InventoryEntity) {
        dao.deleteItem(item)
    }
}
