package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomapp.model.Item

@Dao
interface ItemDao {

    @Insert
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM ITEM_TABLE")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM ITEM_TABLE")
    fun readAllData(): LiveData<List<Item>>
}
