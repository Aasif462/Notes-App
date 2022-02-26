package com.example.notesapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentCreateNotesBinding
import com.example.notesapp.databinding.FragmentHomeBinding

class CreateNotes : Fragment() {

    private lateinit var binding:FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater , container , false )

        binding.done.setOnClickListener{
            startActivity(Intent(context , MainActivity::class.java))
        }

        return binding.root
    }

}