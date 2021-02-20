package com.wordpress.mariaanko.workoutrepcounter.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsHistoryEntity

@Dao
interface WorkoutsHistoryDao {

    @Query("SELECT * FROM workouts_history ORDER BY date_created DESC; ")
    fun getAllSorted(): LiveData<List<WorkoutsHistoryEntity>>

    @Insert
    fun insertAll(vararg workoutsHistoryEntity: WorkoutsHistoryEntity)
}