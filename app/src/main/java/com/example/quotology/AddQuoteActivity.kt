package com.example.quotology

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quotology.databinding.ActivityAddQuoteBinding
import com.google.firebase.database.FirebaseDatabase

class AddQuoteActivity : AppCompatActivity() {

    private val aqa: ActivityAddQuoteBinding by lazy {
        ActivityAddQuoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aqa.root)
        //bind view
        aqa.AddButton.setOnClickListener {
            //get text
            val quote: String = aqa.editTextQuote.text.toString()
            val author: String = aqa.editTextAuthor.text.toString()

            //check if empty
            if (quote.isEmpty()) {
                aqa.editTextQuote.error = "Cannot be empty"
                return@setOnClickListener
            }
            if (author.isEmpty()) {
                aqa.editTextAuthor.error = "Cannot be empty"
                return@setOnClickListener
            }
            //send to database
            addQuoteToDb(quote, author)
        }
    }

    private fun addQuoteToDb(quote: String, author: String) {
        val database = FirebaseDatabase.getInstance()
        val quotesRef = database.getReference("quotes")

        val quoteId = quotesRef.push().key
        if (quoteId != null) {
            val quoteData = mapOf(
                "quote" to quote,
                "author" to author,
                "key" to quoteId
            )
            quotesRef.child(quoteId).setValue(quoteData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Quote added successfully
                    aqa.editTextQuote.text.clear()
                    aqa.editTextAuthor.text.clear()
                } else {
                    // Failed to add quote
                }
            }
        }
    }
}
