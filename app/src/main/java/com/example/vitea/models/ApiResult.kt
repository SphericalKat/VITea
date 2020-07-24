package com.example.vitea.models

import androidx.annotation.Keep

@Keep
data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): ApiResult<T> {
            return ApiResult(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): ApiResult<T> {
            return ApiResult(
                Status.LOADING,
                data,
                null
            )
        }
    }
}