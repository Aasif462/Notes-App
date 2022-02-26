package com.example.notesapp.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

abstract class Database : RoomDatabase()
{
    abstract fun myNotesDao(): NotesDao

    companion object
    {
        @Volatile
        var INSTANCE:Database? = null

        @InternalCoroutinesApi
        fun getDatabaseInstance(context: Context):Database
        {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this)

            {
                val roomDatabaseInstance = Room.databaseBuilder(context , Database::class.java , "Notes").build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }

        }

    }
}