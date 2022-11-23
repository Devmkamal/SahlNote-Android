package com.kamal.sahlsample.data.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> resultNetworkLiveData(networkCall: suspend () -> ResultEndPoint<T>): LiveData<ResultEndPoint<T>> =
    liveData(Dispatchers.IO) {
        emit(ResultEndPoint.loading<T>())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResultEndPoint.Status.SUCCESS) {
            emit(ResultEndPoint.success(responseStatus.data!!))
        } else if (responseStatus.status == ResultEndPoint.Status.ERROR) {
            emit(ResultEndPoint.error<T>(responseStatus.message!!))
        }
    }
