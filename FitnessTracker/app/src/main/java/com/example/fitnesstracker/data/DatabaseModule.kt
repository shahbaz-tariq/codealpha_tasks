package com.example.fitnesstracker.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//DatabaseModule.kt
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitnessDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            FitnessDatabase::class.java,
            "fitness_database"
        ).build()
    }

    @Provides
    fun provideWorkoutDao(database: FitnessDatabase): WorkoutDao{
        return  database.workoutDao()
    }

    @Provides
    fun provideExerciseDao(database: FitnessDatabase): ExerciseDao{
        return  database.exerciseDao()
    }

}