package com.example.daggerdemo

import android.util.Log
import javax.inject.Inject

class MemoryCard @Inject constructor(){
    init {
        Log.i("MYTAG","Memory Card Constructed")
    }
    fun getSpaceAvailability(){
        Log.i("MYTAG","Memory Space available")
    }
}