package com.google.gadsleaderboard.Services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ProjectBuilder {

    //declaring URL for submitting the data entries to google forms
    private const val URL = "https://docs.google.com/forms/d/e/"

    //--
    private val loggers = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //creating OKHttp client
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggers)

    //instantiating the retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    //creating the retrofit builder
    private val retrofit = builder.build()

    fun <T> buildProject(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}