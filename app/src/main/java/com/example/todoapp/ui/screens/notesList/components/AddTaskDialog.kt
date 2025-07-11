package com.example.todoapp.ui.screens.notesList.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.data.models.Task

@Composable
fun AddTaskDialog(
    taskToEdit: Task?,
    onAdd: (String) -> Unit,
    onUpdate: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember {
        mutableStateOf(taskToEdit?.title ?: "")
    }

    AlertDialog(
        containerColor = Color(0xFF85b7c7),
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (taskToEdit == null) onAdd(text) else onUpdate(text)
                }
            ) {
                Text(if (taskToEdit == null) "Dodaj" else "Zapisz")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Anuluj")
            }
        },
        title = { Text(if (taskToEdit == null) "Nowe zadanie" else "Edytuj zadanie") },
        text = {
            OutlinedTextField(
                colors = TextFieldDefaults.colors(),
                value = text,
                label = { Text("Treść zadania") },
                onValueChange = { text = it },
            )
        }
    )
}

@Preview
@Composable
private fun AddTaskDialogPreview() {
    AddTaskDialog(taskToEdit = null, onUpdate = {}, onAdd = {}) { }
}