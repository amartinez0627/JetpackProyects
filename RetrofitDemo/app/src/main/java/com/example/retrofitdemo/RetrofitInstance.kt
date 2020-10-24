package com.example.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


    companion object{
        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun getRetrofitInstance():Retrofit{
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}