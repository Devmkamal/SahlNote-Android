package com.kamal.sahlsample.data.service

import com.kamal.sahlsample.data.models.Note
import com.kamal.sahlsample.data.models.User
import com.kamal.sahlsample.data.models.request.LoginRequest
import com.kamal.sahlsample.data.models.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


@JvmSuppressWildcards
interface NotesApiService {

    @POST("user/login")
    suspend fun login(@Body login: LoginRequest): Response<ResultResponse<User>>

    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ResultResponse<User>>

    @GET("notes")
    suspend fun notes(): Response<ResultResponse<List<Note>>>

    @POST("notes")
    suspend fun addNote(@Body note: String): Response<ResultResponse<Boolean>>

}