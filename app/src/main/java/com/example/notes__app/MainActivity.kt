package com.example.notes__app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() , NoteItemClicked {

    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this ,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
            get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this , { list->
            list?.let {
                adapter.updateNote(it)
            }
        })

        val save = findViewById<Button>(R.id.saveNote)
        save.setOnClickListener {
            val editNote = findViewById<EditText>(R.id.editNote)
            val note = editNote.editableText.toString()
            if(note.isNotEmpty()){
                viewModel.insertNote(Note(note))
                Toast.makeText(this , "Note has been inserted" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this , "Note has been deleted" , Toast.LENGTH_SHORT).show()
    }
}