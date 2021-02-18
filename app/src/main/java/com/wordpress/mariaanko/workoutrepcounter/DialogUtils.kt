package com.wordpress.mariaanko.workoutrepcounter

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.wordpress.mariaanko.workoutrepcounter.adapter.RepsCountAdapter
import com.wordpress.mariaanko.workoutrepcounter.adapter.WorkoutItems

class DialogUtils {
    companion object {
        fun showAddDialog(
            itemsList: ArrayList<WorkoutItems>, repsCountAdapter: RepsCountAdapter,
            layoutInflater: LayoutInflater, context: Context
        ) {
            val addDialog = AlertDialog.Builder(context)
            val addDialogView = layoutInflater.inflate(R.layout.add_dialog, null)

            val workoutNameInput: EditText = addDialogView.findViewById(R.id.workout_name_input)
            val workoutTotalRepsInput: EditText =
                addDialogView.findViewById(R.id.workout_total_reps_input)
            val cancelButton: Button = addDialogView.findViewById(R.id.btn_cancel)
            val okayButton: Button = addDialogView.findViewById(R.id.btn_okay)

            addDialog.setView(addDialogView)
            val alertDialog: AlertDialog = addDialog.create()
            alertDialog.setCanceledOnTouchOutside(false)

            cancelButton.setOnClickListener { alertDialog.dismiss() }

            okayButton.setOnClickListener {
                itemsList.add(
                    WorkoutItems(
                        workoutNameInput.text.toString(),
                        "0",
                        Integer.parseInt(workoutTotalRepsInput.text.toString())
                    )
                )
                repsCountAdapter.notifyDataSetChanged()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        fun showRepsDialog(
            index: Int, itemsList: ArrayList<WorkoutItems>, repsCountAdapter: RepsCountAdapter,
            layoutInflater: LayoutInflater, context: Context
        ) {
            val addDialog = AlertDialog.Builder(context)
            val addDialogView = layoutInflater.inflate(R.layout.reps_dialog, null)

            val workoutDoneRepsInput: EditText =
                addDialogView.findViewById(R.id.workout_done_reps_input)
            val cancelButton: Button = addDialogView.findViewById(R.id.btn_cancel)
            val okayButton: Button = addDialogView.findViewById(R.id.btn_okay)

            addDialog.setView(addDialogView)
            val alertDialog: AlertDialog = addDialog.create()
            alertDialog.setCanceledOnTouchOutside(false)

            cancelButton.setOnClickListener { alertDialog.dismiss() }

            okayButton.setOnClickListener {
                var repsDone = itemsList[index].repsDone
                val repsLeft = itemsList[index].repsLeft
                val repsDoneNow = Integer.parseInt(workoutDoneRepsInput.text.toString())

                if (repsDone.equals("0")) {
                    repsDone = ""
                    itemsList[index].repsDone = "$repsDoneNow"
                } else itemsList[index].repsDone = "$repsDone + $repsDoneNow"
                itemsList[index].repsLeft = repsLeft - repsDoneNow
                repsCountAdapter.notifyDataSetChanged()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}