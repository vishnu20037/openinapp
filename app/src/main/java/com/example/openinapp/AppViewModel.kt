package com.example.openinapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AppViewModel(private val resultRepo: ResultRepo) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            resultRepo.getResults()
        }
    }

    val apiResult: LiveData<ApiResult>
        get() = resultRepo.res

    suspend fun getResults(): Response<ApiResult> {
        return resultRepo.getResults()
    }
}
