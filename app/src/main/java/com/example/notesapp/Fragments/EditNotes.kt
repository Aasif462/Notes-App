package com.example.notesapp.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotes : Fragment() {

    var priority = "1"
    val oldNotes by navArgs<EditNotesArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        binding.title.setText(oldNotes.data.title)
        binding.notes.setText(oldNotes.data.note)

        when(oldNotes.data.priority){
            "1" ->{
                priority = "1"
                binding.green.setImageResource(R.drawable.done_black)
                binding.yellow.setImageResource(0)
                binding.red.setImageResource(0)
            }

            "2" ->{
                priority = "2"
                binding.yellow.setImageResource(R.drawable.done_black)
                binding.green.setImageResource(0)
                binding.red.setImageResource(0)
            }

            "3" ->{
                priority = "3"
                binding.red.setImageResource(R.drawable.done_black)
                binding.green.setImageResource(0)
                binding.yellow.setImageResource(0)
            }
        }

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

        binding.done.setOnClickListener {
            updateNotes(it)
        }

        binding.delete.setOnClickListener {
            deleteNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(view: View){
        val title = binding.title.text.toString()
        val notes = binding.notes.text.toString()
        val date = Date()
        val notesDate:CharSequence = DateFormat.format ("MMMM d yyyy" , date.time)

        val data: Notes = Notes(oldNotes.data.id , title , notes , notesDate.toString() , priority)

        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes Updated Successfully!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(view).navigate(R.id.action_editNotes_to_homeFragment)
    }

    private fun deleteNotes(view: View) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.delete_dialog)

        val yes = bottomSheet.findViewById<TextView>(R.id.yesBtn)
        val no = bottomSheet.findViewById<TextView>(R.id.noBtn)

        yes?.setOnClickListener {
            viewModel.delelteNotes(oldNotes.data.id!!)
            Navigation.findNavController(view).navigate(R.id.action_editNotes_to_homeFragment)

            bottomSheet.dismiss()
        }

        no?.setOnClickListener {
            bottomSheet.dismiss()
        }


        bottomSheet.show()
    }


}