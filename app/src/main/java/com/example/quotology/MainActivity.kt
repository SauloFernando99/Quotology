package com.example.quotology

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotology.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddQuoteActivity::class.java)
            startActivity(intent)
        }

        quoteAdapter = QuoteAdapter(emptyList())
        val recyclerView: RecyclerView = amb.quotesRv
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = quoteAdapter

        fetchQuotesFromFirebase()
    }

    private fun fetchQuotesFromFirebase() {
        val database = FirebaseDatabase.getInstance()
        val quotesRef = database.getReference("quotes")

        quotesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val quotesList = mutableListOf<Quote>()
                for (quoteSnapshot in snapshot.children) {
                    val quote = quoteSnapshot.getValue(Quote::class.java)
                    if (quote != null) {
                        quotesList.add(quote)
                    }
                }
                quoteAdapter.updateQuotes(quotesList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Tratar erro
            }
        })
    }
}
