package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.Database.Database
import com.example.notesapp.Model.Notes
import com.example.notesapp.Repository.NotesRepo
import kotlinx.coroutines.InternalCoroutinesApi

class NotesViewModel(application: Application) : AndroidViewModel(application)
{


        val dao = Database.getDatabaseInstance(application).myNotesDao()
        private val notesRepo:NotesRepo = NotesRepo(dao)

        fun addNotes(notes:Notes){
            notesRepo.insertNotes(notes)
        }

        fun getNotes():LiveData<List<Notes>>
        {
           return notesRepo.getAllNotes()
        }

        fun delelteNotes(id:Int){
            notesRepo.deleteNotes(id)
        }

        fun updateNotes(notes: Notes){
            notesRepo.updateNotes(notes)

    }
}