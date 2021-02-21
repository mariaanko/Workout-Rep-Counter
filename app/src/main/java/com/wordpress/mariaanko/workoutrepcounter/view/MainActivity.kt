package com.wordpress.mariaanko.workoutrepcounter.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wordpress.mariaanko.workoutrepcounter.DialogUtils
import com.wordpress.mariaanko.workoutrepcounter.R
import com.wordpress.mariaanko.workoutrepcounter.adapters.RepsCountAdapter
import com.wordpress.mariaanko.workoutrepcounter.model.WorkoutItems
import com.wordpress.mariaanko.workoutrepcounter.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {

    private var itemsList = ArrayList<WorkoutItems>()
    private lateinit var repsCountAdapter: RepsCountAdapter
    private lateinit var appViewModel: AppViewModel
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        context = this@MainActivity
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        repsCountAdapter = RepsCountAdapter(itemsList, layoutInflater, this, appViewModel)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = repsCountAdapter

        appViewModel.getWorkouts(context).observe(this, Observer {
            if (it.isNotEmpty()) {
                itemsList.clear()
                for ((index, i) in it.withIndex()) {
                    itemsList.add(
                        index,
                        WorkoutItems(i.workoutName, i.repsSummary, i.repsLeft, i.repsDone)
                    )
                }
                repsCountAdapter.notifyDataSetChanged()
            }
        })

        fab.setOnClickListener {
            DialogUtils.showAddDialog(itemsList, layoutInflater, context, appViewModel)
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("currentItems", itemsList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        itemsList.clear()
        itemsList.addAll(savedInstanceState.getParcelableArrayList<WorkoutItems>("currentItems") as ArrayList<WorkoutItems>)
        repsCountAdapter.notifyDataSetChanged()
        appViewModel.saveWorkouts(context, itemsList)
    }

}