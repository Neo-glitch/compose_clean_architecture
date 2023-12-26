package com.example.composecleanapparchitecture.store.presentation.util.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        // elevation to app bar with shadow effect
        modifier = Modifier.shadow(elevation = 5.dp)
    )
}