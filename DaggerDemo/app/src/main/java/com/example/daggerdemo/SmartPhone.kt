package com.example.daggerdemo

import android.util.Log

class SmartPhone(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.i("MYTAG","SmartPhone constructed")
    }

    fun  makeACallWithRecording(){

    }
}