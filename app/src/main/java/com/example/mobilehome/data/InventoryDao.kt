package com.example.mobilehome.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Query("SELECT * FROM inventory_items ORDER BY name ASC")
    fun getAllItemsFlow(): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory_items WHERE category = :category")
    fun getItemsByCategoryFlow(category: String): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory_items WHERE name LIKE '%' || :keyword || '%'")
    fun searchItemsFlow(keyword: String): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory_items WHERE id = :id LIMIT 1")
    fun getItemByIdFlow(id: Int): Flow<InventoryEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryEntity)

    @Update
    suspend fun updateItem(item: InventoryEntity)

    @Delete
    suspend fun deleteItem(item: InventoryEntity)
}
