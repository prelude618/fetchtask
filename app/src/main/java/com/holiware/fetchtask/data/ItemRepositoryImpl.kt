package com.holiware.fetchtask.data

import android.util.Log
import kotlinx.coroutines.flow.flow

class ItemRepositoryImpl(
    private val itemApi: ItemApi
) : ItemRepository {
    override suspend fun getItems() = flow {
        try {
            val items = itemApi.getItem().filter { item ->
                item.name?.isNotEmpty() == true
            }
            emit(items)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            throw e
        }
    }

    companion object {
        const val TAG = "ItemRepositoryImpl"
    }

}