package com.parcial.moviles.data.remote

import com.parcial.moviles.data.model.Product
import com.parcial.moviles.data.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {


    @GET("search?query={text_query}&apiKey={api_token}")
    fun searchByName(
        @Path("api_token") apiToken: String = "1af337454f3043aa8b1b3eb118bc4457",
        @Path("text_query") textQuery: String
    ): Call <ProductResponse>

    @GET("{id_product}?apiKey={api_token}")
    fun searchById(
        @Path("id_product") idProduct: Int,
        @Path("api_token") apiToken: String="1af337454f3043aa8b1b3eb118bc4457"
    ): Call<Product>
}