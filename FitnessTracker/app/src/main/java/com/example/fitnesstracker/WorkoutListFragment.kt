package com.example.fitnesstracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.databinding.FragmentAddWorkoutBinding
import com.example.fitnesstracker.databinding.FragmentWorkoutListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//WorkoutListFragment
@AndroidEntryPoint
class WorkoutListFragment : Fragment() {

    private val viewModel: WorkoutViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WorkoutListAdapter
    private lateinit var binding: FragmentWorkoutListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.workoutList
        adapter = WorkoutListAdapter { workoutId -> onWorkoutItemClick(workoutId) }
        recyclerView.adapter = adapter

        val fabAddWorkout: FloatingActionButton = view.findViewById(R.id.fab_add_workout)
        fabAddWorkout.setOnClickListener { navigateToAddWorkout() }

        observeWorkouts()
        viewModel.getAllWorkouts()
    }

    private fun observeWorkouts() {
        viewModel.allWorkouts.observe(viewLifecycleOwner) { workouts ->
            adapter.submitList(workouts)
        }
    }

    private fun onWorkoutItemClick(workoutId: Int) {
        /*val action =
            WorkoutListFragmentDirections.actionWorkoutListFragmentToWorkoutDetailFragment(workoutId)
        findNavController().navigate(action)*/
    }

    private fun navigateToAddWorkout() {
        findNavController().navigate(R.id.action_workoutListFragment_to_addWorkoutFragment)
    }
}
