package com.example.uiux

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uiux.databinding.ActivityMainBinding
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private  lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)
        binding.notesRecyclearView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclearView.adapter = notesAdapter
        binding.addButton.setOnClickListener {
            val intent = Intent(this, add::class.java)
            startActivity(intent)
        }

    }
    override fun onResume(){
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}