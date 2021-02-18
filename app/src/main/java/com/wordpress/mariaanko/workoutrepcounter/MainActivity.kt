package com.wordpress.mariaanko.workoutrepcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wordpress.mariaanko.workoutrepcounter.adapter.RepsCountAdapter
import com.wordpress.mariaanko.workoutrepcounter.adapter.WorkoutItems

class MainActivity : AppCompatActivity() {

    private var itemsList = ArrayList<WorkoutItems>()
    private lateinit var repsCountAdapter: RepsCountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        repsCountAdapter = RepsCountAdapter(itemsList)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = repsCountAdapter

        addItem()

        fab.setOnClickListener {
            showAddDialog()
        }

    }

    private fun addItem() {

        itemsList.add(WorkoutItems("push-ups", 30, 0))
        repsCountAdapter.notifyDataSetChanged()
    }

    private fun showAddDialog() {
        val addDialog = AlertDialog.Builder(this)
        val addDialogView = layoutInflater.inflate(R.layout.add_dialog, null)

        val workoutNameInput : EditText = addDialogView.findViewById(R.id.workout_name_input)
        val workoutTotalRepsInput : EditText = addDialogView.findViewById(R.id.workout_total_reps_input)
        val cancelButton : Button = addDialogView.findViewById(R.id.btn_cancel)
        val okayButton: Button = addDialogView.findViewById(R.id.btn_okay)

        addDialog.setView(addDialogView)
        val alertDialog: AlertDialog = addDialog.create()
        alertDialog.setCanceledOnTouchOutside(false)

        cancelButton.setOnClickListener { alertDialog.dismiss() }

        okayButton.setOnClickListener {
            itemsList.add(WorkoutItems(workoutNameInput.text.toString(), 0, Integer.parseInt(workoutTotalRepsInput.text.toString())))
            repsCountAdapter.notifyDataSetChanged()
            alertDialog.dismiss()
        }

        alertDialog.show()

    }

}