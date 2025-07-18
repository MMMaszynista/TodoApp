package com.example.todoapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "tasks"
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
)