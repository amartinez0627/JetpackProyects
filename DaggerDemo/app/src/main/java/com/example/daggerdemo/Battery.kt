package com.example.daggerdemo

import android.util.Log
import javax.inject.Inject

class Battery @Inject constructor(){
    init {
        Log.i("MYTAG","Battery constructed")
    }
    fun getPower(){
        Log.i("MYTAG","Battery power is available")
    }
}