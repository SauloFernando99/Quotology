package com.example.quotology

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private var quotes: List<Quote>) : RecyclerView.Adapter<QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.quoteTextView.text = quote.quote
        holder.authorTextView.text = quote.author
    }

    override fun getItemCount(): Int = quotes.size

    fun updateQuotes(newQuotes: List<Quote>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }
}
