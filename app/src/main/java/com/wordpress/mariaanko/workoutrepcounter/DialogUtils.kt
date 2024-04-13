package com.wordpress.mariaanko.workoutrepcounter

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.wordpress.mariaanko.workoutrepcounter.adapters.RepsCountAdapter
import com.wordpress.mariaanko.workoutrepcounter.model.WorkoutItems
import com.wordpress.mariaanko.workoutrepcounter.room.AppDatabase
import com.wordpress.mariaanko.workoutrepcounter.viewmodel.AppViewModel

class DialogUtils {
    companion object {
        fun showAddDialog(
            itemsList: ArrayList<WorkoutItems>,
            layoutInflater: LayoutInflater, context: Context,
            appViewModel: AppViewModel
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
                            workoutName = workoutNameInput.text.toString(),
                            repsDoneSummary = "0",
                            repsLeft = Integer.parseInt(workoutTotalRepsInput.text.toString()),
                            repsLeftInitial = Integer.parseInt(workoutTotalRepsInput.text.toString()),
                            repsDone = 0
                        )
                    )
                }
                appViewModel.saveWorkouts(context, itemsList)
                alertDialog.dismiss()
            }
            alertDialog.show()
        }

        fun showRepsDialog(
            index: Int, itemsList: ArrayList<WorkoutItems>,
            layoutInflater: LayoutInflater, context: Context,
            adapter: RepsCountAdapter,
            appViewModel: AppViewModel
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
                    adapter.notifyDataSetChanged()
                    appViewModel.saveWorkouts(context, itemsList)
                    alertDialog.dismiss()
                } else {
                    alertDialog.dismiss()
                }
            }

            alertDialog.show()
        }

        fun showDeleteDialog(
            index: Int, itemsList: ArrayList<WorkoutItems>,
            layoutInflater: LayoutInflater, context: Context,
            adapter: RepsCountAdapter,
            appViewModel: AppViewModel
        ) {
            val addDialog = AlertDialog.Builder(context)
            val addDialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
            val deleteDialogTextView: TextView =
                addDialogView.findViewById(R.id.delete_dialog_textview)

            val cancelButton: Button = addDialogView.findViewById(R.id.btn_cancel)
            val okayButton: Button = addDialogView.findViewById(R.id.btn_okay)

            addDialog.setView(addDialogView)
            val alertDialog: AlertDialog = addDialog.create()
            alertDialog.setCanceledOnTouchOutside(false)

            deleteDialogTextView.text =
                "Do you want to delete \"${itemsList.get(index).workoutName}\"?"

            cancelButton.setOnClickListener { alertDialog.dismiss() }

            okayButton.setOnClickListener {
                itemsList.removeAt(index)
                adapter.notifyDataSetChanged()
                appViewModel.saveWorkouts(context, itemsList)
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}