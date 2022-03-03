package com.example.notesapp.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import java.util.*

class CreateNotes : Fragment() {
    var priority = "1"

    private lateinit var binding:FragmentCreateNotesBinding
    private val viewModel:NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater , container , false )

        binding.done.setOnClickListener{
            createNotes(it)
        }

        binding.green.setImageResource(R.drawable.done_black)

        binding.green.setOnClickListener{
            priority = "1"
            binding.green.setImageResource(R.drawable.done_black)
            binding.yellow.setImageResource(0)
            binding.red.setImageResource(0)
        }

        binding.yellow.setOnClickListener {
            priority = "2"
            binding.yellow.setImageResource(R.drawable.done_black)
            binding.green.setImageResource(0)
            binding.red.setImageResource(0)
        }

        binding.red.setOnClickListener {
            priority = "3"
            binding.red.setImageResource(R.drawable.done_black)
            binding.green.setImageResource(0)
            binding.yellow.setImageResource(0)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.title.text.toString()
        val notes = binding.notes.text.toString()
        val date = Date()
        val notesDate:CharSequence = DateFormat.format ("MMMM d yyyy" , date.time)

        if(title.isNotEmpty() || notes.isNotEmpty()){
            val data:Notes = Notes(null , title , notes , notesDate.toString() , priority)

            viewModel.addNotes(data)
            Navigation.findNavController(it!!).navigate(R.id.action_createNotes_to_homeFragment)

        }
        else{
            Toast.makeText(context, "Title or Notes Missing!", Toast.LENGTH_SHORT).show()
        }
    }
}