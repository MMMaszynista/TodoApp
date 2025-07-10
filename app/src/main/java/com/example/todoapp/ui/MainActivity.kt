package com.example.todoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.screens.notesList.TaskListScreen
import com.example.todoapp.ui.screens.notesList.TaskViewModel
import com.example.todoapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                val viewModel: TaskViewModel = hiltViewModel()
                TaskListScreen(viewModel)
            }
        }
    }
}