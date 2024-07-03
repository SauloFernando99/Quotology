package com.example.quotology

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val quoteTextView: TextView = itemView.findViewById(R.id.quoteTv)
    val authorTextView: TextView = itemView.findViewById(R.id.authorTv)
}
