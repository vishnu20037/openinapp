package com.example.openinapp

import android.content.Context
import com.example.openinapp.Constants.BASE_URL
import com.example.openinapp.Constants.PREFS_NAME
import com.example.openinapp.Constants.TOKEN_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private var token: String? = null
    fun getInstance(context: Context): Retrofit {
        token = getTokenFromLocalStorage(context)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(newRequest)
        }).build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getTokenFromLocalStorage(context: Context): String? {
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
        AppDelegate.saveTokenToLocalStorage(context, token)
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TOKEN_KEY, null)
    }
}
