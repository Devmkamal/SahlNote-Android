package com.kamal.sahlsample.data.service

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.networkResponse?.code == 401) {
            //PopupUtils.tokenExpired()
        }
        return response
    }

}