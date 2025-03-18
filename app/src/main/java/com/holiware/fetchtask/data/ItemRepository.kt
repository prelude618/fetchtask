package com.holiware.fetchtask.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItems(): Flow<List<Item>>
}