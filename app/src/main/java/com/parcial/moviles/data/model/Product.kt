package com.parcial.moviles.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    @SerializedName("title")
    val name: String,
    val image: String,
    var isFavorite: Boolean
)

data class ProductTitle(
    val title: String
)

data class ProductResponse (
    @SerializedName("products")
    val products: List<Product>
)

