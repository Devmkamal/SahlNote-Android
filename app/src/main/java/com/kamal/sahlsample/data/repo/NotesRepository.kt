package com.kamal.sahlsample.data.repo


import com.kamal.sahlsample.data.models.request.LoginRequest
import com.kamal.sahlsample.data.models.request.RegisterRequest
import com.kamal.sahlsample.data.service.resultNetworkLiveData
import com.kamal.sahlsample.data.source.NotesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(
    private val remoteSource: NotesRemoteDataSource
) {

    fun login(login: LoginRequest) =
        resultNetworkLiveData(networkCall = {
            remoteSource.login(
                login
            )
        })

    fun register(registerRequest: RegisterRequest) =
        resultNetworkLiveData(networkCall = {
            remoteSource.register(
                registerRequest
            )
        })

    fun notes() =
        resultNetworkLiveData(networkCall = {
            remoteSource.notes()
        })

    fun addNote(note: String) =
        resultNetworkLiveData(networkCall = {
            remoteSource.addNote(
                note
            )
        })


}
