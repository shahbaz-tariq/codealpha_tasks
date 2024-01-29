package com.example.fitnesstracker.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//WorkoutRepo.kt
class WorkoutRepository @Inject constructor(private val workoutDao: WorkoutDao) {

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutDao.getAllWorkouts()
    }

    suspend fun getWorkoutById(workoutId: Int): Workout? {
        return workoutDao.getWorkoutById(workoutId)
    }

    suspend fun insertWorkout(workout: Workout) {
        return workoutDao.insertWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }

    fun getWorkoutsWithExercises(): Flow<List<WorkoutWithExercises>> = flow {
        emit(workoutDao.getWorkoutsWithExercises())
    }
}
