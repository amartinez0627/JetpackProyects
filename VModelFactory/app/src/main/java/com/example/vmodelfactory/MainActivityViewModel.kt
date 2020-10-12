package com.example.vmodelfactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingValue:Int): ViewModel() {
//    var total = MutableLiveData<Int>()
    private var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
    get() = total

    init {
        total.value = startingValue
    }

    fun addTotal(input:Int){
        total.value = (total.value)?.plus(input)
    }
}