package com.kamal.sahlsample.data.service

import android.content.SharedPreferences
import com.kamal.sahlsample.common.K_USER
import com.kamal.sahlsample.data.models.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AuthInterceptor @Inject constructor(val sharedPreferences: SharedPreferences,val  json: Json) : Interceptor {
    val user: User?
    get() {
        return if (sharedPreferences.contains(K_USER)) json.decodeFromString<User>(
            sharedPreferences.getString(K_USER,"").toString()
        ) else null
    }
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token =  user?.token
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}