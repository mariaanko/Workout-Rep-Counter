package com.wordpress.mariaanko.workoutrepcounter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.mariaanko.workoutrepcounter.R

internal class RepsCountAdapter (private var itemsList: ArrayList<WorkoutItems>) :
    RecyclerView.Adapter<RepsCountAdapter.RepsViewHolder>() {

    internal inner class RepsViewHolder(view: View): RecyclerView.ViewHolder(view){
        var workoutNameTextView: TextView = view.findViewById(R.id.workout_name)
        var repsLeftTextView: TextView = view.findViewById(R.id.reps_left)
        var totalRepsTextView: TextView = view.findViewById(R.id.reps_total)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rep_items, parent,false)
        return RepsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: RepsViewHolder, position: Int) {
        val item = itemsList.get(position)
        holder.workoutNameTextView.text = item.workoutName
        holder.totalRepsTextView.text = item.totalReps.toString()
        holder.repsLeftTextView.text = item.repsLeft.toString()
    }

}