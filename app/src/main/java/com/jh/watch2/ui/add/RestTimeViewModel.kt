package com.jh.watch2.ui.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.watch2.App
import com.jh.watch2.db.TimerModel
import com.jh.watch2.ui.timer.TimerActivity
import com.jh.watch2.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RestTimeViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val clickNext = SingleLiveEvent<Unit>()

    fun onClickNext() {
        clickNext.call()
    }

    fun convertDataToTimerModel(restMin: Int, restSec: Int): TimerModel {
        val setCount = savedStateHandle.get<Int>(SetCountActivity.EXTRA_SET_COUNT) ?: 0
        val setTime = savedStateHandle.get<Long>(SetTimeActivity.EXTRA_SET_TIME) ?: 0L
        val restTime = (restMin * 60000 + restSec * 1000).toLong()
        return TimerModel("${Random(100).nextInt()}", setTime, restTime, setCount)
    }

    fun insertTimer(restMin: Int, restSec: Int) {
        val setCount = savedStateHandle.get<Int>(SetCountActivity.EXTRA_SET_COUNT) ?: 0
        val setTime = savedStateHandle.get<Long>(SetTimeActivity.EXTRA_SET_TIME) ?: 0L
        val restTime = (restMin * 60000 + restSec * 1000).toLong()
        val timerModel = TimerModel("${Random(100).nextInt()}", setTime, restTime, setCount)
        viewModelScope.launch(Dispatchers.IO) {
            App.db.timerDao().insert(timerModel)
        }
    }

    fun updateTimer(restMin: Int, restSec: Int) {
        val setCount = savedStateHandle.get<Int>(SetCountActivity.EXTRA_SET_COUNT) ?: 0
        val setTime = savedStateHandle.get<Long>(SetTimeActivity.EXTRA_SET_TIME) ?: 0L
        val restTime = (restMin * 60000 + restSec * 1000).toLong()
        val timerModel = TimerModel("${Random(100).nextInt()}", setTime, restTime, setCount)
        viewModelScope.launch(Dispatchers.IO) {
            App.db.timerDao().insert(timerModel)
        }
    }

    fun getWhereFrom(): Int = savedStateHandle.get<Int>(TimerActivity.EXTRA_FROM) ?: 0

}