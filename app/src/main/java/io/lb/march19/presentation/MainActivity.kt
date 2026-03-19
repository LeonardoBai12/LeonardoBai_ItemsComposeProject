package io.lb.march19.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.lb.march19.domain.GetItemsUseCase
import io.lb.march19.presentation.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ItemViewModel = ItemViewModel(GetItemsUseCase())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _root_ide_package_.io.lb.march19.presentation.ui.theme.March19Theme {
                val state = viewModel.state.collectAsState().value
                ItemsScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    eventFlow = viewModel.eventFlow
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _root_ide_package_.io.lb.march19.presentation.ui.theme.March19Theme {
        Greeting("Android")
    }
}