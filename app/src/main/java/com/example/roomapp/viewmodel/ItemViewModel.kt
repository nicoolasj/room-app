package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.ItemDatabase
import com.example.roomapp.repository.ItemRepository
import com.example.roomapp.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ItemRepository
    val readAllData: LiveData<List<Item>>

    init {
        val itemDao = ItemDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }

    fun insertItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }
}
