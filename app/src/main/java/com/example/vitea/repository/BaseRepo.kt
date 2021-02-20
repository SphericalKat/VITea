package com.example.vitea.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.vitea.models.ApiResult
import kotlinx.coroutines.Dispatchers

open class BaseRepo {
    protected fun <T> makeRequest(request: suspend () -> ApiResult<T>) = liveData {
        emit(ApiResult.loading())

        val response = request.invoke()

        when (response.status) {
            ApiResult.Status.SUCCESS -> {
                emit(response)
            }
            ApiResult.Status.ERROR -> {
                emit(response)
            }
            else -> {
            }
        }
    }

    protected fun <T, A> makeRequestAndSave(
        databaseQuery: () -> LiveData<T>,
        networkCall: suspend () -> ApiResult<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<ApiResult<T>> = liveData(Dispatchers.IO) {
        emit(ApiResult.loading())

        val source = databaseQuery.invoke().map { ApiResult.success(it) }
        emitSource(source)

        val response = networkCall.invoke()
        when (response.status) {
            ApiResult.Status.SUCCESS -> {
                saveCallResult(response.data!!)
            }
            ApiResult.Status.ERROR -> {
                emit(ApiResult.error(response.message!!))
                emitSource(source)
            }
            else -> {}
        }
    }
}