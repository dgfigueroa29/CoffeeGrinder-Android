package com.interview.coffeegrinder.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.coffeegrinder.R
import com.interview.coffeegrinder.model.Coffee
import java.lang.ref.WeakReference

class CoffeeAdapter(
    private val context: WeakReference<Context>,
    private val listener: OnSelectItem<Coffee?>
) :
    RecyclerView.Adapter<ListViewHolder>() {
    private var list = listOf<Coffee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context.get()).inflate(
            R.layout.item_view,
            parent,
            false
        )


        return ListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.itemName.text = item.name
        holder.itemPrice.text = "$${item.price}"

        holder.itemName.setOnClickListener { listener.onSelectItem(item) }
        holder.itemPrice.setOnClickListener { listener.onSelectItem(item) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Coffee>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}