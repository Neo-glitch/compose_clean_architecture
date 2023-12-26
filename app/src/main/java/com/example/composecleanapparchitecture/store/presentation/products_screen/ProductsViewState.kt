package com.example.composecleanapparchitecture.store.presentation.products_screen

import com.example.composecleanapparchitecture.store.domain.model.Product

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)
