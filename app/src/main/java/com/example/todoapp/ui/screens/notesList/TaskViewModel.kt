package com.example.todoapp.ui.screens.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TaskRepository
import com.example.todoapp.data.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository
        .allTasks
        .stateIn(
            viewModelScope, SharingStarted.Lazily,
            emptyList()
        )

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _taskBeingEdited = MutableStateFlow<Task?>(null)
    val taskBeingEdited: StateFlow<Task?> = _taskBeingEdited

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.insert(Task(title = title))
            _showDialog.value = false
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun editTask(task: Task) {
        _taskBeingEdited.value = task
        _showDialog.value = true
    }

    fun updateTask(newTitle: String) {
        val original = _taskBeingEdited.value
        original?.let {
            viewModelScope.launch {
                repository.update(original.copy(title = newTitle))
                _taskBeingEdited.value = null
                _showDialog.value = false
            }
        }
    }

    fun onAddTaskClicked() {
        _showDialog.value = true
    }

    fun dismissDialog() {
        _showDialog.value = false
    }

}