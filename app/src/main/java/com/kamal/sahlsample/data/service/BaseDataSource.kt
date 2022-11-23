package com.kamal.sahlsample.data.service

import retrofit2.Response
import timber.log.Timber


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultEndPoint<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Timber.d("API_SENT --> ${body}")
                if (body != null)
                    return ResultEndPoint.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResultEndPoint<T> {
        Timber.e(message)
        return ResultEndPoint.error(message)
    }

}