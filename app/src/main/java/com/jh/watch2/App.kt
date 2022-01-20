package com.jh.watch2

import android.app.Application
import androidx.room.Room
import com.jh.watch2.db.TimerDB

class App: Application() {
    companion object {
        lateinit var db: TimerDB
    }

    override fun onCreate() {
        db = Room.databaseBuilder( this, TimerDB::class.java, "TimerDB")
            .fallbackToDestructiveMigrationFrom()
            .allowMainThreadQueries()
            .build()
        super.onCreate()
    }
}