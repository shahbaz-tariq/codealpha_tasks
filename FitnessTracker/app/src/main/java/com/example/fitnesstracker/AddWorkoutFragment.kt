package com.example.fitnesstracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.data.Workout
import com.example.fitnesstracker.databinding.FragmentAddWorkoutBinding
import dagger.hilt.android.AndroidEntryPoint

// AddWorkoutFragment.kt
@AndroidEntryPoint
class AddWorkoutFragment : Fragment() {

    private val viewModel: WorkoutViewModel by viewModels()

    private lateinit var editTextWorkoutName: EditText
    private lateinit var buttonAddWorkout: Button
    private lateinit var binding: FragmentAddWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWorkoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextWorkoutName = view.findViewById(R.id.editTextWorkoutName)
        buttonAddWorkout = view.findViewById(R.id.buttonAddWorkout)

        buttonAddWorkout.setOnClickListener {
            val workoutName = editTextWorkoutName.text.toString()

            if (workoutName.isNotEmpty()) {
                val newWorkout = Workout(name = workoutName)
                viewModel.insertWorkout(newWorkout)
                // Optionally, navigate back to the WorkoutListFragment or perform other actions
                val action =
                    AddWorkoutFragmentDirections.actionAddWorkoutFragmentToWorkoutListFragment()
                findNavController().navigate(action)
            } else {
                // Handle the case where the workout name is empty
                Toast.makeText(requireContext(), "Workout name cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
