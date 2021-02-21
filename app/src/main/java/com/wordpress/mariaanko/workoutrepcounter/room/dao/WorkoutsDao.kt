package com.wordpress.mariaanko.workoutrepcounter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsEntity

@Dao
interface WorkoutsDao {
    @Query("SELECT * FROM workouts")
    fun getAll(): LiveData<List<WorkoutsEntity>>

    @Insert
    fun insertAll(vararg workoutsEntity: WorkoutsEntity)

    @Query("DELETE FROM workouts")
    fun deleteAll()

}