package com.wordpress.mariaanko.workoutrepcounter.adapter

data class WorkoutItems(
    var workoutName: String,
    var repsDoneSummary: String,
    var repsLeft: Int,
    var repsDone: Int
)