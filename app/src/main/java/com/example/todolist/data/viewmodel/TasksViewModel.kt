package com.example.todolist.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TasksDatabase
import com.example.todolist.data.models.TasksData
import com.example.todolist.data.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val tasksDao = TasksDatabase.getDatabase(application).tasksDao()

    private val repository:TasksRepository

    private val getAllData : LiveData<List<TasksData>>

    init {
        repository = TasksRepository(tasksDao)
        getAllData = repository.getAllData
    }

    fun insertData(tasksData: TasksData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(tasksData)
        }
    }


}