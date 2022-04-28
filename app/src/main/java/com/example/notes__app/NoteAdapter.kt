package com.example.notes__app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val listener: NoteItemClicked) : RecyclerView.Adapter<NoteViewHolder>() {

    val items : ArrayList<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_list , parent , false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.deleteImageView.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = items[position]
        holder.note.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNote(updatedNote: List<Note>){
        items.clear()
        items.addAll(updatedNote)

        notifyDataSetChanged()
    }
}

class NoteViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    val note = itemView.findViewById<TextView>(R.id.note)
    val deleteImageView = itemView.findViewById<ImageView>(R.id.deleteImageView)
}

interface NoteItemClicked{
    fun onItemClicked(note: Note)
}