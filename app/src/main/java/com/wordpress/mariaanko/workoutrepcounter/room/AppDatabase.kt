package com.wordpress.mariaanko.workoutrepcounter.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wordpress.mariaanko.workoutrepcounter.room.dao.WorkoutsDao
import com.wordpress.mariaanko.workoutrepcounter.room.dao.WorkoutsHistoryDao
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsEntity
import com.wordpress.mariaanko.workoutrepcounter.room.model.WorkoutsHistoryEntity

@Database(entities = [WorkoutsEntity::class, WorkoutsHistoryEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun workoutsDao(): WorkoutsDao
    abstract fun workoutsHistoryDao() : WorkoutsHistoryDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabaseClient(context: Context): AppDatabase{
            if(INSTANCE != null ) return INSTANCE!!

            synchronized(this){
                INSTANCE = Room.databaseBuilder( context,
                    AppDatabase::class.java, "APP_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }

    }

}