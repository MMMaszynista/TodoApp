package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.models.Task

@Database(entities = [Task::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}