package com.jh.watch2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database( entities = [ TimerModel::class ], version = 1, exportSchema = false )
abstract class TimerDB: RoomDatabase() {
    abstract fun timerDao(): TimerDAO
}