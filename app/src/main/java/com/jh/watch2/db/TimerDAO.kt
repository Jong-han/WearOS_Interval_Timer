package com.jh.watch2.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class TimerDAO {

    @Query("SELECT * FROM Timer")
    abstract fun getAll(): LiveData<List<TimerModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(timerModel: TimerModel)

    @Update
    abstract fun update(timerModel: TimerModel)

}