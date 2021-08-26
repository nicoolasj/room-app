package com.example.roomapp.data

import com.example.roomapp.model.Item

class ItemRepository(private val itemDao: ItemDao) {

    val readAllData = itemDao.readAllData()

    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    suspend fun deleteAllItems() {
        itemDao.deleteAllItems()
    }
}
