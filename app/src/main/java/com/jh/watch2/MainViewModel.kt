package com.jh.watch2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val text = MutableLiveData<String>("dataBinding test")
}