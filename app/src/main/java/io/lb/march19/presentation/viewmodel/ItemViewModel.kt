package io.lb.march19.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lb.march19.domain.GetItemsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {
    sealed interface ItemUiEvent {
        data class ShowToast(val text: String) : ItemUiEvent
    }

    private val _state: MutableStateFlow<ItemsState> = MutableStateFlow(ItemsState())
    val state = _state.asStateFlow()

    private val _eventFlow : MutableSharedFlow<ItemUiEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.OnClickItem -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        ItemUiEvent.ShowToast(event.item)
                    )
                }
            }

            ItemEvent.GetItems -> {
                _state.update {
                    it.copy(
                        items = getItemsUseCase()
                    )
                }
            }
        }
    }
}
