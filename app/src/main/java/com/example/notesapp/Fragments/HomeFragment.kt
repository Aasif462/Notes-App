package com.example.notesapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.Adapter.NotesAdapter
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    val viewModel: NotesViewModel by viewModels()

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.addNote.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotes)
        }

        viewModel.getNotes().observe(viewLifecycleOwner)
        {
            notesList ->

            binding.recView.layoutManager = LinearLayoutManager(context )
            binding.recView.adapter = NotesAdapter(requireContext() , notesList)
        }

        return binding.root
    }

}