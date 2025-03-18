package com.holiware.fetchtask.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiware.fetchtask.data.Item
import com.holiware.fetchtask.data.ItemRepository
import com.holiware.fetchtask.view.NetworkStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ItemViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _itemListState = MutableStateFlow<List<Item>>(emptyList())
    val itemListState: MutableStateFlow<List<Item>> = _itemListState

    val networkStatus = MutableSharedFlow<NetworkStatus>()

    init {
        getItems()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getItems() {
        viewModelScope.launch {
            networkStatus.emit(NetworkStatus.LOADING)
            try {
                itemRepository.getItems().collectLatest { items ->
                    _itemListState.value = items.sortedBy {
                        it.listId
                    }.groupBy {
                        it.listId
                    }.mapValues { (_, group) ->
                        group.sortedBy {
                            it.name
                        }
                    }.values.flatten()
                }
                networkStatus.emit(NetworkStatus.SUCCESS)
            } catch (e: Exception) {
                networkStatus.emit(NetworkStatus.FAILURE)
            }
        }
    }
}