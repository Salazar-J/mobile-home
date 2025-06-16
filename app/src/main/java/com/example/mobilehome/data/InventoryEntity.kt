package com.example.mobilehome.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val quantity: Int,
    val purchasePrice: Double,
    val purchaseDate: String,
    val warrantyMonths: Int,
    val expirationDate: String?
)
