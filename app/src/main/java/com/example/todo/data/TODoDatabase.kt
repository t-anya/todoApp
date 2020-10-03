package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.data.model.ToDoData

@Database(entities = [ToDoData::class], version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class TODoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

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

//    companion object {
//        @Volatile
//        private var INSTANCE: TODoDatabase? = null
//
//        fun getDatabase(context: Context): TODoDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE
//                    ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                TODoDatabase::class.java, "todo_database"
//            ).build()
//    }

}