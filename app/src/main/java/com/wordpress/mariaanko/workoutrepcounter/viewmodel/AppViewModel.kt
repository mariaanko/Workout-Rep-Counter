package com.wordpress.mariaanko.workoutrepcounter.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wordpress.mariaanko.workoutrepcounter.model.WorkoutItems
import com.wordpress.mariaanko.workoutrepcounter.repository.AppRepository
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsEntity
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.collections.ArrayList

class AppViewModel : ViewModel() {

    fun getWorkouts(context: Context): LiveData<List<WorkoutsEntity>> {
        return AppRepository.getWorkouts(context)
    }

    fun saveWorkouts(context: Context, workoutItems: ArrayList<WorkoutItems>) {
        val thread = Thread{
            AppRepository.deleteWorkouts(context)

            for ( workoutItem in workoutItems) {
                AppRepository.insertWorkout(
                    context,
                    WorkoutsEntity(
                        workoutName = workoutItem.workoutName,
                        repsSummary = workoutItem.repsDoneSummary,
                        repsLeft = workoutItem.repsLeft,
                        repsDone = workoutItem.repsDone
                    )
                )
            }
        }
        thread.start()
    }
}