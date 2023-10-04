package com.parcial.moviles.data.remote

import com.parcial.moviles.data.model.Product
import com.parcial.moviles.data.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {


    @GET("search")
    fun searchByName(
        @Query("apiKey") apiToken: String = "1af337454f3043aa8b1b3eb118bc4457",
        @Query("query") textQuery: String
    ): Call <ProductResponse>

    @GET("{id_product}")
    fun searchById(
        @Path("id_product") idProduct: Int,
        @Query("apiKey") apiToken: String="1af337454f3043aa8b1b3eb118bc4457"
    ): Call<Product>
}