package com.example.notesapp.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.Model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@androidx.room.Database(entities = [Notes::class],version = 1 , exportSchema = false)
abstract class Database : RoomDatabase()
{
    abstract fun myNotesDao(): NotesDao

    companion object
    {
        @Volatile
        var INSTANCE:Database? = null

        fun getDatabaseInstance(context: Context):Database
        {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            kotlin.synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(context , Database::class.java , "Notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }

        }

    }
}