package com.example.notesapp.Fragments

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Adapter.NotesAdapter
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    val viewModel: NotesViewModel by viewModels()

    private lateinit var binding:FragmentHomeBinding
    private lateinit var adapter: NotesAdapter
    private var oldNotes = ArrayList<Notes>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        searchNotes()

        binding.addNote.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotes)
        }

        binding.alltxt.setBackgroundResource(R.drawable.select_filter_background)
        binding.alltxt.typeface = Typeface.DEFAULT_BOLD

        binding.alltxt.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner)
            {
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext() , notesList)
                binding.recView.layoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL )
                binding.recView.adapter = adapter
            }
            allTextBackground(it)
        }

        binding.hightxt.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner)
            {
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext() , notesList)
                binding.recView.layoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL )
                binding.recView.adapter = adapter
            }
            highTextBackground(it)
        }

        binding.mediumtxt.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner)
            {
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext() , notesList)
                binding.recView.layoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL )
                binding.recView.adapter = adapter
            }
            mediumTextBackground(it)
        }

        binding.lowtxt.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner)
            {
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext() , notesList)
                binding.recView.layoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL )
                binding.recView.adapter = adapter
            }
            lowTextBackground(it)
        }

        viewModel.getNotes().observe(viewLifecycleOwner)
        {
            notesList ->

            adapter = NotesAdapter(requireContext() , notesList)
            binding.recView.layoutManager = StaggeredGridLayoutManager(2 ,LinearLayoutManager.VERTICAL )
            binding.recView.adapter = adapter
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })

        return binding.root
    }

    private fun allTextBackground(view:View){
        binding.alltxt.typeface = Typeface.DEFAULT_BOLD
        binding.hightxt.typeface = Typeface.DEFAULT
        binding.mediumtxt.typeface = Typeface.DEFAULT
        binding.lowtxt.typeface = Typeface.DEFAULT

        binding.alltxt.setBackgroundResource(R.drawable.select_filter_background)
        binding.hightxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.mediumtxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.lowtxt.setBackgroundResource(R.drawable.text_custom_background)
    }

    private fun highTextBackground(view: View){
        binding.alltxt.typeface = Typeface.DEFAULT
        binding.hightxt.typeface = Typeface.DEFAULT_BOLD
        binding.mediumtxt.typeface = Typeface.DEFAULT
        binding.lowtxt.typeface = Typeface.DEFAULT

        binding.hightxt.typeface = Typeface.DEFAULT_BOLD
        binding.alltxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.hightxt.setBackgroundResource(R.drawable.select_filter_background)
        binding.mediumtxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.lowtxt.setBackgroundResource(R.drawable.text_custom_background)
    }

    private fun mediumTextBackground(view:View){
        binding.alltxt.typeface = Typeface.DEFAULT_BOLD
        binding.hightxt.typeface = Typeface.DEFAULT
        binding.mediumtxt.typeface = Typeface.DEFAULT_BOLD
        binding.lowtxt.typeface = Typeface.DEFAULT

        binding.mediumtxt.typeface = Typeface.DEFAULT_BOLD
        binding.alltxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.hightxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.mediumtxt.setBackgroundResource(R.drawable.select_filter_background)
        binding.lowtxt.setBackgroundResource(R.drawable.text_custom_background)
    }

    private fun lowTextBackground(view:View){
        binding.alltxt.typeface = Typeface.DEFAULT_BOLD
        binding.hightxt.typeface = Typeface.DEFAULT
        binding.mediumtxt.typeface = Typeface.DEFAULT
        binding.lowtxt.typeface = Typeface.DEFAULT_BOLD

        binding.lowtxt.typeface = Typeface.DEFAULT_BOLD
        binding.alltxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.hightxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.mediumtxt.setBackgroundResource(R.drawable.text_custom_background)
        binding.lowtxt.setBackgroundResource(R.drawable.select_filter_background)
    }

    private fun searchNotes(){
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })
    }

    private fun NotesFiltering(p0: String?) {
        val newFilteredList = ArrayList<Notes>()
        for(i in oldNotes){
            if(i.title!!.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}