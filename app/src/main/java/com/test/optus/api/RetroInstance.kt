package com.test.optus.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        var httpIceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var httpClnt = OkHttpClient.Builder().addInterceptor(httpIceptor).build()
        fun getRetroInstance(): Retrofit {
            //println("hello world")
            return Retrofit.Builder()
                .client(httpClnt)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}