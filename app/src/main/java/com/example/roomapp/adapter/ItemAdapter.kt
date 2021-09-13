package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.Item
import com.example.roomapp.viewmodel.ItemViewModel
import com.google.android.material.checkbox.MaterialCheckBox

class ItemAdapter(private val listener: OnItemClickListener, private val viewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var itemData = emptyList<Item>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val checkBox: MaterialCheckBox = view.findViewById(R.id.checkBox)

        init {
            view.setOnClickListener(this)
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemData[position].item
        holder.imageView.setOnClickListener {
            viewModel.deleteItem(itemData[position])
        }
        holder.checkBox.isChecked = itemData[position].completed
        holder.checkBox.setOnClickListener {
            viewModel.updateItem(
                Item(
                    itemData[position].id,
                    itemData[position].item,
                    holder.checkBox.isChecked
                )
            )
        }
    }

    override fun getItemCount(): Int = itemData.size

    fun itemList(itemData: List<Item>) {
        this.itemData = itemData
        notifyDataSetChanged()
    }
}
