package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ToDoData::class], version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract  class TODoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoData

    companion object{

        @Volatile
        private var INSTANCE: TODoDatabase?= null

        fun getDatabase(context: Context):TODoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TODoDatabase::class.java,
                    "todo_database"
                ).build()

                INSTANCE = instance
                return instance

            }
        }

    }

}