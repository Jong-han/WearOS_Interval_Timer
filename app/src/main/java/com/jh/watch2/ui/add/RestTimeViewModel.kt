package com.jh.watch2.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.watch2.TimerModel
import com.jh.watch2.util.SingleLiveEvent

class RestTimeViewModel : ViewModel() {

    val clickNext = SingleLiveEvent<Unit>()

    fun onClickNext() {
        clickNext.call()
    }

}