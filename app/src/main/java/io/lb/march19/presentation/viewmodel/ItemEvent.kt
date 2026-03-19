package io.lb.march19.presentation.viewmodel

sealed interface ItemEvent {
    object GetItems : ItemEvent
    data class OnClickItem(val item: String) : ItemEvent
}
