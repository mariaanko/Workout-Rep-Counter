package com.wordpress.mariaanko.workoutrepcounter

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

                if (workoutNameInput.text.toString() == "") {
                    Toast.makeText(context, "Please enter workout name!", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                } else if (workoutTotalRepsInput.text.toString() == "") {
                    Toast.makeText(context, "Please enter total reps!", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                } else {
                    itemsList.add(
                        WorkoutItems(
                            workoutNameInput.text.toString(),
                            "0",
                            Integer.parseInt(workoutTotalRepsInput.text.toString()),
                            0
                        )
                    )
                }

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

                if (workoutDoneRepsInput.text.toString() != "") {
                    val repsDoneSummary = itemsList[index].repsDoneSummary
                    val repsDone = itemsList[index].repsDone
                    val repsLeft = itemsList[index].repsLeft
                    val repsDoneNow = Integer.parseInt(workoutDoneRepsInput.text.toString())

                    if (repsDoneSummary.equals("0")) {
                        itemsList[index].repsDoneSummary = "$repsDoneNow"
                    } else itemsList[index].repsDoneSummary = "$repsDoneSummary + $repsDoneNow"
                    itemsList[index].repsLeft = repsLeft - repsDoneNow
                    itemsList[index].repsDone = repsDone + repsDoneNow
                    repsCountAdapter.notifyDataSetChanged()
                    alertDialog.dismiss()
                } else {
                    alertDialog.dismiss()
                }
            }

            alertDialog.show()
        }
    }
}