package com.parcial.moviles.repository

import com.parcial.moviles.data.local.ProductDao
import com.parcial.moviles.data.local.ProductEntity
import com.parcial.moviles.data.model.Product
import com.parcial.moviles.data.model.ProductResponse
import com.parcial.moviles.data.remote.ApiClient
import com.parcial.moviles.data.remote.ProductService
import com.parcial.moviles.utils.Result

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(
    private val productDao: ProductDao,
    private val productService: ProductService = ApiClient.getProductService()
) {
    fun searchById(id: Int, callback: (Result<Product>)->Unit){
        val searchById = productService.searchById(id)

        searchById.enqueue(object: Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>){
                if (response.isSuccessful){
                    callback(Result.Success(response.body()!!))
                }
            }
            override fun onFailure(call: Call<Product>, t: Throwable){
                callback(Result.Error(t.localizedMessage as String))
            }
        })
    }
    fun searchByName(name: String, callback: (Result<List<Product>>)-> Unit){
        val searchByName = productService.searchByName(textQuery = name)
        searchByName.enqueue(object: Callback<ProductResponse>{
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>){
                if (response.isSuccessful){
                    try{
                        val products = response.body()!!.products
                        products.forEach{product ->
                            product.isFavorite = productDao.getById(product.id) != null
                        }
                        callback(Result.Success(response.body()!!.products))
                    } catch (e: Exception){
                        callback(Result.Success(listOf<Product>()))
                    }
                }
            }
            override fun onFailure(call: Call<ProductResponse>, t: Throwable){
                callback(Result.Error(t.localizedMessage as String))
            }
        })
    }
    fun save(product: Product){
        productDao.save(ProductEntity(product.id))
        product.isFavorite = true
    }

    fun delete(product: Product){
        productDao.delete(ProductEntity(product.id))
        product.isFavorite = false
    }
}