package com.example.composecleanapparchitecture.store.presentation.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composecleanapparchitecture.store.presentation.products_screen.components.ProductCard
import com.example.composecleanapparchitecture.store.presentation.util.components.LoadingDialog
import com.example.composecleanapparchitecture.store.presentation.util.components.MyTopAppBar

// this will contain the viewmodel and navController objects
@Composable
internal fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel()
) {
    // collects state in lifecycle aware way
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductsContent(state = state)
}


// this will contain just state and UI alone to allow for ease of ui testing
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsContent(
    state: ProductsViewState
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "products")
        }
    ) {
        // similar to GridView with just 2 columns
        LazyVerticalStaggeredGrid(
            // once topAppBar added, must use this modifier to leave space for topAppBar
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(state.products){ product ->
                ProductCard(product = product)
            }
        }
    }

}