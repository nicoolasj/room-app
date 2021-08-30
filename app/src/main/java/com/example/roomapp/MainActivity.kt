package com.example.roomapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.adapter.ItemAdapter
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.model.Item
import com.example.roomapp.viewmodel.ItemViewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        val adapter = ItemAdapter(this, itemViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel.readAllData.observe(
            this,
            {
                adapter.itemList(it)
                Log.d(TAG, "$it")
            }
        )

        binding.button.setOnClickListener {
            itemViewModel.insertItem(Item(0, "${binding.editText.text}"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) itemViewModel.deleteAllItems()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = itemViewModel.readAllData.value!![position]
        itemViewModel.updateItem(Item(item.id, "${binding.editText.text}"))
    }
}
