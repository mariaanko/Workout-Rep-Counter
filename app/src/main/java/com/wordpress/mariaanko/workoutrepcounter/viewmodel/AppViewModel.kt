package com.wordpress.mariaanko.workoutrepcounter.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wordpress.mariaanko.workoutrepcounter.repository.AppRepository
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsEntity

class AppViewModel: ViewModel() {

    fun getWorkouts(context: Context): LiveData<List<WorkoutsEntity>>{
        return AppRepository.getWorkouts(context)
    }
}