package com.example.daggerdemo

import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule {

    @Provides
    fun proviedesMemoryCard(): MemoryCard{
        return  MemoryCard()
    }
}