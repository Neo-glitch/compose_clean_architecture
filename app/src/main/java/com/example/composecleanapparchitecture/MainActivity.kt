package com.example.composecleanapparchitecture

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.composecleanapparchitecture.store.presentation.products_screen.ProductsScreen
import com.example.composecleanapparchitecture.ui.theme.ComposeCleanAppArchitectureTheme
import com.example.composecleanapparchitecture.util.Event
import com.example.composecleanapparchitecture.util.EventBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCleanAppArchitectureTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                val context = LocalContext.current
                LaunchedEffect(key1 = lifecycle) {
                    // launches this whenever lifecycle object changes
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        EventBus.events.collectLatest { event ->
                            if (event is Event.Toast)
                                Toast.makeText(context, event.message, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductsScreen()
                }
            }
        }
    }
}

