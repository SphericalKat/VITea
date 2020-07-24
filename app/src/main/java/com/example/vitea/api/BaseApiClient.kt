package com.example.vitea.api

import com.example.vitea.BuildConfig
import com.example.vitea.models.ApiResult
import retrofit2.Response

open class BaseApiClient {
    protected suspend fun <T> getResult(request: suspend () -> Response<T>): ApiResult<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ApiResult.success(body)
                } else {
                    ApiResult.error("Server response error")
                }
            } else {
                ApiResult.error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: e.toString()
            return if (BuildConfig.DEBUG) {
                ApiResult.error("Network call failed with message $errorMessage")
            } else {
                ApiResult.error("Check your internet connection!")
            }
        }
    }
}