package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.TaskDao
import com.example.todoapp.data.TaskDatabase
import com.example.todoapp.data.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: TaskDatabase): TaskDao = db.taskDao()

    @Provides
    @Singleton
    fun provideRepository(dao: TaskDao): TaskRepository = TaskRepository(dao)

}