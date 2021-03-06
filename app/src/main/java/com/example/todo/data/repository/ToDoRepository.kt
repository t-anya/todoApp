package com.example.todo.data.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.ToDoDao
import com.example.todo.data.model.ToDoData

class ToDoRepository(private  val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}