package com.jh.watch2.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jh.watch2.TimerModel
import com.jh.watch2.ui.main.MainActivity

class TimerViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    sealed class TimeType(val time: Long) {
        class Set(time: Long): TimeType(time)
        class Rest(time: Long): TimeType(time)
    }

    private val timerModel = savedStateHandle.get<TimerModel>(MainActivity.EXTRA_TIMER)
    var totalSetCount = 0
    var setTime = 0L
    var restTime = 0L
    var currentSet = MutableLiveData<Int>(1)
    val timeType = MutableLiveData<TimeType>()
    init {
        timerModel?.let {
            totalSetCount = it.setCount
            setTime = it.setInterval
            restTime = it.restInterval
            timeType.value = TimeType.Set(setTime)
        }
    }
    fun switchTimeType(currentType: TimeType) {
        timeType.value = when (currentType) {
            is TimeType.Set -> {
                currentSet.value = currentSet.value?.plus(1)
                TimeType.Rest(restTime)
            }
            is TimeType.Rest -> TimeType.Set(setTime)
        }
    }
    fun isRemainSet(): Boolean = currentSet.value ?: 1 < totalSetCount
}