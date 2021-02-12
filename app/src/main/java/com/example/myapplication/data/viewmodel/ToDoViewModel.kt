package com.example.myapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ToDoDatabase
import com.example.myapplication.data.models.TodoData
import com.example.myapplication.data.reprository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) :AndroidViewModel(application){
    private val toDoDao=
        ToDoDatabase.getDatabase(
            application
        ).toDoDao()
    private val repository: ToDoRepository

    private val getAllData: LiveData<List<TodoData>>

    init {
        repository=ToDoRepository(toDoDao)
        getAllData=repository.getAllData
    }
    fun insertData(todoData: TodoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertData(todoData)
        }
    }

}