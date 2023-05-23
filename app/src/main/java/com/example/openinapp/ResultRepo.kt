package com.example.openinapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response

class ResultRepo(private val openInAppApi: OpenInAppApi) {
    private val resultLiveData = MutableLiveData<ApiResult>()
    val res: LiveData<ApiResult>
    get() = resultLiveData
    suspend fun getResults(): Response<ApiResult> {
        return openInAppApi.getResults()
    }
}