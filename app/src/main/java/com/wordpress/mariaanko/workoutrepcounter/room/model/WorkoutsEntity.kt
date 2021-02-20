package com.wordpress.mariaanko.workoutrepcounter.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutsEntity (
            @PrimaryKey(autoGenerate = true) val uid: Int = 0,
            @ColumnInfo(name = "workout_name") val workoutName: String?,
            @ColumnInfo(name = "reps_summary") val repsSummary: String?,
            @ColumnInfo(name = "reps_left") val repsLeft: Int,
            @ColumnInfo(name = "reps_done") val repsDone: Int
        )
