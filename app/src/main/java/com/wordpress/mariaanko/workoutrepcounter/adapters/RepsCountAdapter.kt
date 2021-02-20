package com.wordpress.mariaanko.workoutrepcounter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.mariaanko.workoutrepcounter.DialogUtils
import com.wordpress.mariaanko.workoutrepcounter.R
import com.wordpress.mariaanko.workoutrepcounter.model.WorkoutItems
import com.wordpress.mariaanko.workoutrepcounter.room.AppDatabase

class RepsCountAdapter(
    private var itemsList: ArrayList<WorkoutItems>,
    val inflater: LayoutInflater,
    val context: Context,
) :
    RecyclerView.Adapter<RepsCountAdapter.RepsViewHolder>() {

    inner class RepsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var workoutNameTextView: TextView = view.findViewById(R.id.workout_name)
        var repsLeftTextView: TextView = view.findViewById(R.id.reps_left)
        var repsDoneSummaryTextView: TextView = view.findViewById(R.id.reps_summary)
        var repsDone: TextView = view.findViewById(R.id.reps_total)
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
        holder.repsDone.text = item.repsDone.toString()
        holder.repsDoneSummaryTextView.text = item.repsDoneSummary
        holder.repsLeftTextView.text = item.repsLeft.toString()
        holder.itemView.setOnClickListener {
            DialogUtils.showRepsDialog(position, itemsList, inflater, context, this)
        }
        holder.itemView.setOnLongClickListener {
            DialogUtils.showDeleteDialog(position, itemsList, inflater, context, this)
            true
        }
    }

}