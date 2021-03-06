package com.google.gadsleaderboard.Services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    //declaring URL
    private const val URL = "https://gadsapi.herokuapp.com/"

    private val loggers = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //creating OKHttp client
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggers)

    //instantiating the retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    //creating the retrofit builder
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}