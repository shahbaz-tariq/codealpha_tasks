package com.example.fitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.data.Exercise

class ExerciseListAdapter(private val onItemClick: (exerciseId: Int) -> Unit) :
    ListAdapter<Exercise, ExerciseListAdapter.ExerciseViewHolder>(DiffCallback()) {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseSetsTextView: TextView = itemView.findViewById(R.id.exerciseSets)
        val exerciseRepsTextView: TextView = itemView.findViewById(R.id.exerciseReps)
        val exerciseWeightTextView: TextView = itemView.findViewById(R.id.exerciseWeight)
        val exerciseTargetMuscleTextView: TextView = itemView.findViewById(R.id.exerciseTargetMuscle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        holder.exerciseNameTextView.text = exercise.name
        holder.exerciseSetsTextView.text = "Sets: ${exercise.sets}"
        holder.exerciseRepsTextView.text = "Reps: ${exercise.reps}"
        holder.exerciseWeightTextView.text = "Weight: ${exercise.weight} kgs"
        holder.exerciseTargetMuscleTextView.text = "Target Muscle: ${exercise.targetMuscleGroup}"

        holder.itemView.setOnClickListener {
            onItemClick(exercise.id)  // Handle item click
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }
}

