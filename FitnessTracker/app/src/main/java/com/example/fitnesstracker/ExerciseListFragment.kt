package com.example.fitnesstracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

//ExerciseListFragment.kt
@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExerciseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.exercise_list)
        adapter = ExerciseListAdapter{ exerciseId -> onExerciseItemClick(exerciseId) }
        recyclerView.adapter = adapter

        observeExercises()
        viewModel.getAllExercises()
    }

    private fun observeExercises() {
        viewModel.allExercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }
    }

    private fun onExerciseItemClick(exerciseId: Int) {
        // Handle item click if needed
    }
}
