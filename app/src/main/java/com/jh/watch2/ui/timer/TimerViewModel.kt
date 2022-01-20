package com.jh.watch2.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jh.watch2.db.TimerModel
import com.jh.watch2.ui.main.MainActivity
import com.jh.watch2.util.SingleLiveEvent
import java.util.concurrent.TimeUnit

class TimerViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    sealed class TimeType(val time: Long) {
        class Set(time: Long): TimeType(time)
        class Rest(time: Long): TimeType(time)
    }

    private val timerModel = savedStateHandle.get<TimerModel>(MainActivity.EXTRA_TIMER)
    var totalSetCount = 0
    var setTime = ""
    var restTime = ""
    var currentSet = MutableLiveData<Int>(0)
    val timeType = MutableLiveData<TimeType>()

    val clickStart = SingleLiveEvent<Unit>()
    val clickEdit = SingleLiveEvent<Unit>()

    init {
        timerModel?.let {
            totalSetCount = it.setCount
            setTime = String.format("%d분 %d초",
                TimeUnit.MILLISECONDS.toMinutes(it.setInterval),
                TimeUnit.MILLISECONDS.toSeconds(it.setInterval) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(it.setInterval))
            )
            restTime = String.format("%d분 %d초",
                TimeUnit.MILLISECONDS.toMinutes(it.restInterval),
                TimeUnit.MILLISECONDS.toSeconds(it.restInterval) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(it.restInterval))
            )
            timeType.value = TimeType.Set(it.setInterval)
        }
    }

    fun onClickStart() {
        if (currentSet.value != 0) {
            resetTimer()
        }
        clickStart.call()
    }

    fun onClickEdit() {
        clickEdit.call()
    }

    fun switchTimeType(currentType: TimeType) {
        timeType.value = when (currentType) {
            is TimeType.Set -> {
                increaseCurrentCount()
                TimeType.Rest(timerModel?.restInterval ?: 0L)
            }
            is TimeType.Rest -> TimeType.Set(timerModel?.setInterval ?: 0L)
        }
    }

    private fun increaseCurrentCount() {
        if ( currentSet.value ?: 0 < totalSetCount )
            currentSet.value = currentSet.value?.plus(1)
    }

    fun isRemainSet(): Boolean = currentSet.value ?: 0 < totalSetCount

    private fun resetTimer() {
        currentSet.value = 0
        timeType.value = TimeType.Set(timerModel?.setInterval ?: 0L)
    }
}