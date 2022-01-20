package com.jh.watch2.ui.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jh.watch2.ui.timer.TimerActivity
import com.jh.watch2.util.SingleLiveEvent

class SetCountViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val clickNext = SingleLiveEvent<Unit>()

    fun onClickNext() {
        clickNext.call()
    }

    fun getWhereFrom(): Int = savedStateHandle.get<Int>(TimerActivity.EXTRA_FROM) ?: 0
}