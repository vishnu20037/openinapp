package com.example.openinapp

import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    val BASE_URL = "https://api.inopenapp.com/api/v1/"
    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
            )
            .build()
        chain.proceed(newRequest)
    }).build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}