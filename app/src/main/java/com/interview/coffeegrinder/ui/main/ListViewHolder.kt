package com.interview.coffeegrinder.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.interview.coffeegrinder.R

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemName: TextView = view.findViewById(R.id.tvName)
    val itemPrice: TextView = view.findViewById(R.id.tvPrice)
}