package com.example.composecleanapparchitecture.store.presentation.products_screen

import android.provider.CalendarContract.Events
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecleanapparchitecture.store.domain.repository.ProductRepository
import com.example.composecleanapparchitecture.store.presentation.util.sendEvent
import com.example.composecleanapparchitecture.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state = _state.asStateFlow()

    init{
        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            productsRepository.getProducts()
                .onRight { products ->
                    // response
                    _state.update { it.copy(products = products) }
                }
                .onLeft { networkError ->
                    //error
                    _state.update { it.copy(error = networkError.error.message) }
                    sendEvent(Event.Toast(networkError.error.message))
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}