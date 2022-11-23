package com.kamal.sahlsample.data.source

import com.kamal.sahlsample.data.models.request.LoginRequest
import com.kamal.sahlsample.data.models.request.RegisterRequest
import com.kamal.sahlsample.data.service.BaseDataSource
import com.kamal.sahlsample.data.service.NotesApiService
import javax.inject.Inject


class NotesRemoteDataSource @Inject constructor(private val serviceServices: NotesApiService) :
    BaseDataSource() {

    suspend fun login(login: LoginRequest) =
        getResult { serviceServices.login(login) }

    suspend fun register(registerRequest: RegisterRequest) =
        getResult { serviceServices.register(registerRequest) }


    suspend fun notes() =
        getResult { serviceServices.notes() }

    suspend fun addNote(note :String) =
        getResult { serviceServices.addNote(note) }

}
