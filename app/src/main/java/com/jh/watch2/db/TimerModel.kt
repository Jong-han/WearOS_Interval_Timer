package com.jh.watch2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Timer")
data class TimerModel(var title: String, var setInterval: Long, var restInterval: Long, var setCount: Int): Parcelable {
    @IgnoredOnParcel
    @PrimaryKey( autoGenerate = true )
    var id: Int = 0
}