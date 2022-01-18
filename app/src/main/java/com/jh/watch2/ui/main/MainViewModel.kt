package com.jh.watch2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.watch2.TimerModel
import com.jh.watch2.util.SingleLiveEvent

class MainViewModel: ViewModel() {

    val clickAdd = SingleLiveEvent<Unit>()
    val timerList = MutableLiveData<ArrayList<TimerModel>>()

    fun onClickAdd() {
        clickAdd.call()
    }
    fun addTimer() {
        val temp = timerList.value ?: arrayListOf()
        temp.add(TimerModel("타이머", 3000L, 1000L, 3))
        timerList.value = temp
    }
}