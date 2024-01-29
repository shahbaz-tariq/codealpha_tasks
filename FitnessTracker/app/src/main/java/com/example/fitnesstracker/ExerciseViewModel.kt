package com.example.fitnesstracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.Exercise
import com.example.fitnesstracker.data.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ExerciseViewModel.kt
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _exercisesForWorkout = MutableLiveData<List<Exercise>>()
    val exercisesForWorkout: LiveData<List<Exercise>> get() = _exercisesForWorkout

    private val _selectedExercise = MutableLiveData<Exercise>()
    val selectedExercise: LiveData<Exercise> get() = _selectedExercise

    private val _allExercises = MutableLiveData<List<Exercise>>()
    val allExercises: LiveData<List<Exercise>> get() = _allExercises

    fun getAllExercises() {
        viewModelScope.launch {
            _allExercises.value = exerciseRepository.getAllExercises()
        }
    }

    fun getExercisesForWorkout(workoutId: Long) {
        viewModelScope.launch {
            _exercisesForWorkout.value = exerciseRepository.getExercisesForWorkout(workoutId)
        }
    }

    fun getExerciseById(exerciseId: Long) {
        viewModelScope.launch {
            _selectedExercise.value = exerciseRepository.getExerciseById(exerciseId)
        }
    }

    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.insertExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.deleteExercise(exercise)
        }
    }

}
