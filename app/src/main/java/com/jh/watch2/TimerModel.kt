package com.jh.watch2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimerModel(var title: String, var setInterval: Long, var restInterval: Long, var setCount: Int): Parcelable