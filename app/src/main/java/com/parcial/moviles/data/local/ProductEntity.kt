package com.parcial.moviles.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
)