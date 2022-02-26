package com.example.notesapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.addNote.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotes)
        }

        return binding.root
    }

}