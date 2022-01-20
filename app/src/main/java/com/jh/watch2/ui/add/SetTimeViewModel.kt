package com.jh.watch2.ui.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jh.watch2.App
import com.jh.watch2.db.TimerModel
import com.jh.watch2.ui.timer.TimerActivity
import com.jh.watch2.util.SingleLiveEvent

class SetTimeViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    val clickNext = SingleLiveEvent<Unit>()

    fun onClickNext() {
        clickNext.call()
    }

    fun convertTimeToLong(min: Int, sec: Int): Long {
        return (min*60000 + sec*1000).toLong()
    }

    fun getSetCount() = savedStateHandle.get<Int>(SetCountActivity.EXTRA_SET_COUNT) ?: 0

    fun getWhereFrom(): Int = savedStateHandle.get<Int>(TimerActivity.EXTRA_FROM) ?: 0
}