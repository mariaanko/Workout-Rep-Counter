package com.wordpress.mariaanko.workoutrepcounter

import android.content.Intent
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
            DialogUtils.showAddDialog(itemsList, repsCountAdapter, layoutInflater, this)
        }

    }

    private fun addItem() {

        itemsList.add(WorkoutItems("push-ups", 30, 0))
        itemsList.add(WorkoutItems("sit-ups", 45, 0))
        repsCountAdapter.notifyDataSetChanged()
    }

}