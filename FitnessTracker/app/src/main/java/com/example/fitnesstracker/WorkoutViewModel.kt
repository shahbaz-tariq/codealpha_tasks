package com.example.fitnesstracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.Workout
import com.example.fitnesstracker.data.WorkoutRepository
import com.example.fitnesstracker.data.WorkoutWithExercises
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

// WorkoutViewModel.kt
@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    /*val allWorkoutsWithExercises: LiveData<List<WorkoutWithExercises>> =
        liveData {
            emitSource(workoutRepository.getWorkoutsWithExercises().asLiveData())
        }*/

    private val _allWorkouts = MutableLiveData<List<Workout>>()
    val allWorkouts: LiveData<List<Workout>> get() = _allWorkouts

    private val _selectedWorkout = MutableLiveData<Workout>()
    val selectedWorkout: LiveData<Workout> get() = _selectedWorkout

    fun getAllWorkouts() {
        viewModelScope.launch {
            val workouts = workoutRepository.getAllWorkouts()
            _allWorkouts.value = workouts
            Log.d("WorkoutViewModel", "Fetched ${workouts.size} workouts")
        }
    }

    fun getWorkoutById(workoutId: Int) {
        viewModelScope.launch {
            _selectedWorkout.value = workoutRepository.getWorkoutById(workoutId)
        }
    }

    /*fun insertWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutRepository.insertWorkout(workout)
        }
    }*/

    fun insertWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutRepository.insertWorkout(workout)
            getAllWorkouts()
            //_selectedWorkout.value = workoutRepository.getWorkoutById(workoutId) //error here
        }
    }

    fun updateWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutRepository.updateWorkout(workout)
        }
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workout)
        }
    }

    val allWorkoutsWithExercises: Flow<List<WorkoutWithExercises>> =
        workoutRepository.getWorkoutsWithExercises()

}
