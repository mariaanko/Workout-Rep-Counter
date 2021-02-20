package com.wordpress.mariaanko.workoutrepcounter.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "workouts_history")
data class WorkoutsHistoryEntity (
            @PrimaryKey val uid: Int,
            @ColumnInfo(name = "workout_name") val workoutName: String?,
            @ColumnInfo(name = "reps_summary") val repsSummary: String?,
            @ColumnInfo(name = "reps_left") val repsLeft: Int?,
            @ColumnInfo(name = "reps_done") val repsDone: Int?,
            @ColumnInfo(name = "date_created") val dateCreated: Date?
        )
