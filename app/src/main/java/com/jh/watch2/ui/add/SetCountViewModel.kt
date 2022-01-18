package com.jh.watch2.ui.add

import androidx.lifecycle.ViewModel
import com.jh.watch2.util.SingleLiveEvent

class SetCountViewModel : ViewModel() {
    val clickNext = SingleLiveEvent<Unit>()

    fun onClickNext() {
        clickNext.call()
    }
}