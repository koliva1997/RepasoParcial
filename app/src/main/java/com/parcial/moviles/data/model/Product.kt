package com.parcial.moviles.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val name: ProductTitle,
    val image: ProductImage,
    var isFavorite: Boolean
)

data class ProductTitle(
    val title: String
)

data class ProductImage(
    val url: String
)

data class ProductResponse (
    @SerializedName("results")
    val products: List<Product>
)

