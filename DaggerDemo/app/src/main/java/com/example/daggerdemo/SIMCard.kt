package com.example.daggerdemo

import android.util.Log

class SIMCard(private val serviceProvider: ServiceProvider) {
    init {
        Log.i("MYTAG","SIM Card constructed")
    }
    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}