package com.example.notesapp.Adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Fragments.CreateNotes
import com.example.notesapp.Fragments.HomeFragment
import com.example.notesapp.Fragments.HomeFragmentDirections
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import org.w3c.dom.Text

class NotesAdapter(val requireContext:Context , var notesList:List<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }


    class ViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.description.text = data.note.toString()
        holder.binding.date.text = data.date.toString()

        holder.binding.cardView.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotes(data)
            Navigation.findNavController(it).navigate(action)
        }

        when(data.priority){
            "1" ->{
                holder.binding.priority.setBackgroundResource(R.drawable.green_dot)
            }

            "2" ->{
                holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)
            }

            "3" ->{
                holder.binding.priority.setBackgroundResource(R.drawable.red_dot)
            }
        }
    }

    override fun getItemCount(): Int  = notesList.size


}