package com.example.notesapp.Database

import com.example.notesapp.Dao.NotesDao

abstract class Database{
    abstract fun myNotesDao(): NotesDao

    companion object
    {

    }
}