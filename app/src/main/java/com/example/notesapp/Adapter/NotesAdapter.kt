package com.example.notesapp.Adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Fragments.CreateNotes
import com.example.notesapp.Fragments.HomeFragment
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemNotesBinding
import org.w3c.dom.Text

class NotesAdapter(val requireContext:Context , val notesList:List<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


    class ViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.description.text = data.note
        holder.binding.date.text = data.date

        holder.binding.cardView.setOnClickListener{
            val action = R.id.action_homeFragment_to_createNotes
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.cardView.setOnLongClickListener {
            val dialog = Dialog(requireContext)
            dialog.setContentView(R.layout.delete_dialog)
            dialog.show()
            return@setOnLongClickListener true

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

private fun Intent.putExtra(s: String, priority: View) {

}

private fun Intent.putExtra(s: String, notesTitle: TextView) {

}
