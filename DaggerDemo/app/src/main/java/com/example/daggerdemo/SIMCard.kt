package com.example.daggerdemo

import android.util.Log
import javax.inject.Inject

class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {
    init {
        Log.i("MYTAG","SIM Card constructed")
    }
    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}