package com.example.fitnesstracker.data

import javax.inject.Inject

//ExerciseRepo.kt
class ExerciseRepository @Inject constructor (private val exerciseDao: ExerciseDao) {

    suspend fun getExercisesForWorkout(workoutId: Long): List<Exercise> {
        return exerciseDao.getExercisesForWorkout(workoutId)
    }

    suspend fun getExerciseById(exerciseId: Long): Exercise? {
        return exerciseDao.getExerciseById(exerciseId)
    }

    suspend fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
    }

    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }
}
