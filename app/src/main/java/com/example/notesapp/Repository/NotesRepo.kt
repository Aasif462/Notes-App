package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Model.Notes

class NotesRepo (private val dao:NotesDao){

    fun getAllNotes() :LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

}