package com.example.openinapp

import retrofit2.Response
import retrofit2.http.GET


interface OpenInAppApi {
    @GET("dashboardNew")
    suspend fun getResults(): Response<ApiResult>
}