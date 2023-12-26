package com.example.composecleanapparchitecture.store.domain.repository

import arrow.core.Either
import com.example.composecleanapparchitecture.store.domain.model.NetworkError
import com.example.composecleanapparchitecture.store.domain.model.Product

interface ProductRepository {

    // Either used here, the left value is error and right value is success response
    // saves us stress of trying to use try and catch in repository functions
    suspend fun getProducts(): Either<NetworkError, List<Product>>
}