package com.example.vmodelfactory

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingValue:Int): ViewModel() {
    private var total = 0

    init {
        total = startingValue
    }
    fun getTotal():Int{
        return total
    }

    fun addTotal(input:Int){
        total += input
    }
}