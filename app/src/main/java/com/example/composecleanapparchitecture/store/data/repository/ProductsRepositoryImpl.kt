package com.example.composecleanapparchitecture.store.data.repository

import arrow.core.Either
import com.example.composecleanapparchitecture.store.data.mapper.toNetworkError
import com.example.composecleanapparchitecture.store.data.remote.ProductsApi
import com.example.composecleanapparchitecture.store.domain.model.NetworkError
import com.example.composecleanapparchitecture.store.domain.model.Product
import com.example.composecleanapparchitecture.store.domain.repository.ProductRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productsApi: ProductsApi) : ProductRepository {

    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toNetworkError() }
    }
}