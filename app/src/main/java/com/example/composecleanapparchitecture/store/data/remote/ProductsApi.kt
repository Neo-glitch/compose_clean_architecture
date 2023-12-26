package com.example.composecleanapparchitecture.store.data.remote

import com.example.composecleanapparchitecture.store.domain.model.Product
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<Product>
}