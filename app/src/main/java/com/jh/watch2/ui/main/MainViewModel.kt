package com.jh.watch2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.watch2.App
import com.jh.watch2.db.TimerModel
import com.jh.watch2.util.SingleLiveEvent

class MainViewModel: ViewModel() {
    val clickAdd = SingleLiveEvent<Unit>()
    val timerList = App.db.timerDao().getAll()

    fun onClickAdd() {
        clickAdd.call()
    }
}