package com.example.todoapp.ui.screens.notesList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.screens.notesList.components.AddTaskDialog
import com.example.todoapp.ui.screens.notesList.components.TaskItem
import com.example.todoapp.ui.theme.LeafGreen
import com.example.todoapp.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Pencil Icon",
                        tint = Color.White
                    )
                },
                colors = topAppBarColors(
                    containerColor = Color(0xFF0c4513),
                    titleContentColor = Color.White
                ),
                title = {
                    Text("Moje zadania")
                }
            )

        },
        containerColor = LightGreen,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = LeafGreen,
                onClick = {
                    viewModel.onAddTaskClicked()
                }
            ) {
                Text("+", fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
            ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(task = task, onDelete = {
                        viewModel.deleteTask(task)
                    })
                }
            }
        }

        if (showDialog) {
            AddTaskDialog(
                onAdd = { title -> viewModel.addTask(title) },
                onDismiss = { viewModel.dismissDialog() }
            )
        }
    }
}

@Preview
@Composable
private fun TaskListScreenPreview() {
    TaskListScreen()
}