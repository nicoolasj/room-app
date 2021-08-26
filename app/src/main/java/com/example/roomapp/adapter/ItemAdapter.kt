package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.Item

class ItemAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var itemData = emptyList<Item>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener,
        View.OnLongClickListener {

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(position)
            }
            return true
        }

        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemData[position].item
    }

    override fun getItemCount(): Int = itemData.size

    fun itemList(itemData: List<Item>) {
        this.itemData = itemData
        notifyDataSetChanged()
    }
}
