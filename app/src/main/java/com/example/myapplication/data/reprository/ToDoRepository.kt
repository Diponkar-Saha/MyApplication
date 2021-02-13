package com.example.myapplication.data.reprository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.ToDoDao
import com.example.myapplication.data.models.TodoData



class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<TodoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: TodoData){
        toDoDao.insertData(toDoData)
    }

}