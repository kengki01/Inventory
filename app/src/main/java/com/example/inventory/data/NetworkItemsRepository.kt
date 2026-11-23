package com.example.inventory.data

import com.example.inventory.network.InventoryApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NetworkItemsRepository(
    private val apiService: InventoryApiService
) : ItemsRepository {

    override fun getAllItemsStream(): Flow<List<Item>> = flow {
        while (true) {
            try {
                emit(apiService.getAllItems())
            } catch (e: Exception) {
                emit(emptyList())
            }
            delay(2000) // Auto-refresh data dari MySQL setiap 2 detik
        }
    }

    override fun getItemStream(id: Int): Flow<Item?> =
        getAllItemsStream().map { list -> list.find { it.id == id } }

    override suspend fun insertItem(item: Item) {
        apiService.insertItem(item.name, item.price, item.quantity)
    }

    override suspend fun deleteItem(item: Item) {
        apiService.deleteItem(item.id)
    }

    override suspend fun updateItem(item: Item) {
        apiService.updateItem(item.id, item.name, item.price, item.quantity)
    }
}