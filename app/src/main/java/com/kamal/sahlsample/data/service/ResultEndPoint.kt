package com.kamal.sahlsample.data.service


data class ResultEndPoint<out T>(val status: Status, val data: T?, val message: String?) {

    // response status
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResultEndPoint<T> {
            return ResultEndPoint(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResultEndPoint<T> {
            return ResultEndPoint(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResultEndPoint<T> {
            return ResultEndPoint(Status.LOADING, data, null)
        }
    }
}