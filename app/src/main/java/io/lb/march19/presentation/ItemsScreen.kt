package io.lb.march19.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.lb.march19.presentation.viewmodel.ItemEvent
import io.lb.march19.presentation.viewmodel.ItemViewModel
import io.lb.march19.presentation.viewmodel.ItemsState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ItemsScreen(
    modifier: Modifier = Modifier,
    state: ItemsState,
    onEvent: (ItemEvent) -> Unit,
    eventFlow: SharedFlow<ItemViewModel.ItemUiEvent>
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            onEvent(ItemEvent.GetItems)
        }

        LaunchedEffect(eventFlow.toString()) {
            eventFlow.collectLatest { event ->
                when (event) {
                    is ItemViewModel.ItemUiEvent.ShowToast -> {
                        Toast.makeText(
                            context,
                            "You clicked ${event.text}!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(state.items) { item ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ItemEvent.OnClickItem(item))
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = item
                    )
                }
            }
        }
    }
}
