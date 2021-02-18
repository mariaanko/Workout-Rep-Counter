package com.wordpress.mariaanko.workoutrepcounter.adapter

import android.os.Parcel
import android.os.Parcelable

data class WorkoutItems(
    var workoutName: String,
    var repsDoneSummary: String,
    var repsLeft: Int,
    var repsDone: Int
) : Parcelable{
    constructor(parcel : Parcel): this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(workoutName)
        parcel.writeString(repsDoneSummary)
        parcel.writeInt(repsLeft)
        parcel.writeInt(repsDone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkoutItems> {
        override fun createFromParcel(parcel: Parcel): WorkoutItems {
            return WorkoutItems(parcel)
        }

        override fun newArray(size: Int): Array<WorkoutItems?> {
            return arrayOfNulls(size)
        }
    }
}