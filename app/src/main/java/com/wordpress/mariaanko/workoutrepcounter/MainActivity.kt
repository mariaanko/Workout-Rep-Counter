package com.wordpress.mariaanko.workoutrepcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        repsCountAdapter = RepsCountAdapter(itemsList, layoutInflater, this )

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = repsCountAdapter

        addItem()

        fab.setOnClickListener {
            DialogUtils.showAddDialog(itemsList, repsCountAdapter, layoutInflater, this)
        }

    }

    private fun addItem() {

        itemsList.add(WorkoutItems("push-ups", "0", 30))
        itemsList.add(WorkoutItems("sit-ups", "0", 25))
        repsCountAdapter.notifyDataSetChanged()
    }

}