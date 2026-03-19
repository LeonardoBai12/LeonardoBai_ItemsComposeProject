package io.lb.march19.domain

class GetItemsUseCase {
    operator fun invoke(): List<String> {
        return listOf("item1", "item2", "item3", "item4")
    }
}
