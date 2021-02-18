package com.wordpress.mariaanko.workoutrepcounter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.mariaanko.workoutrepcounter.DialogUtils
import com.wordpress.mariaanko.workoutrepcounter.R

class RepsCountAdapter(
    private var itemsList: ArrayList<WorkoutItems>,
    val inflater: LayoutInflater,
    val context: Context
) :
    RecyclerView.Adapter<RepsCountAdapter.RepsViewHolder>() {

    inner class RepsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var workoutNameTextView: TextView = view.findViewById(R.id.workout_name)
        var repsLeftTextView: TextView = view.findViewById(R.id.reps_left)
        var repsDoneTextView: TextView = view.findViewById(R.id.reps_done)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rep_items, parent, false)
        return RepsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(
        holder: RepsViewHolder,
        position: Int
    ) {
        val item = itemsList.get(position)
        holder.workoutNameTextView.text = item.workoutName
        holder.repsDoneTextView.text = item.repsDone
        holder.repsLeftTextView.text = item.repsLeft.toString()
        holder.itemView.setOnClickListener {
            val item = itemsList.get(position)
            DialogUtils.showRepsDialog(position, itemsList, this, inflater, context)
        }
    }

}