package com.wordpress.mariaanko.workoutrepcounter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.wordpress.mariaanko.workoutrepcounter.room.AppDatabase
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsEntity

class AppRepository {

    companion object {

        var appDatabase: AppDatabase? = null

        fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDatabaseClient(context)
        }

        fun getWorkouts(context: Context): LiveData<List<WorkoutsEntity>> {
            appDatabase = initializeDB(context)
            return appDatabase!!.workoutsDao().getAll()
        }

    }
}