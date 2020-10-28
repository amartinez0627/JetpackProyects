package com.example.daggerdemo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule (val memorySize: Int){

    @Provides
    fun proviedesMemoryCard(): MemoryCard{
        Log.i("MYTAG","Size of memory is $memorySize")
        return  MemoryCard()
    }
}