package com.example.notesapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

class CreateNotes : Fragment() {
    var priority = "1"

    private lateinit var binding:FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater , container , false )

        binding.done.setOnClickListener{
            createNotes(it)
            startActivity(Intent(context , MainActivity::class.java))
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
        var title = binding.title.text.toString()
        var notes = binding.notes.text.toString()

        val date = Date()
        val notesDate:CharSequence = DateFormat.format ("MMMM d yyyy" , date.time)


    }


}