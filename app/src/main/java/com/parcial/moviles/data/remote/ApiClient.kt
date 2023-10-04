package com.parcial.moviles.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://api.spoonacular.com/food/products/"
    private var productService: ProductService? = null

    fun getProductService(): ProductService{

        if(productService == null){
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            productService = retrofit.create(ProductService::class.java)
        }

        return productService as ProductService
    }
}