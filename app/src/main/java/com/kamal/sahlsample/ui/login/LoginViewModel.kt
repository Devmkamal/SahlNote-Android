package com.kamal.sahlsample.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamal.sahlsample.common.K_USER
import com.kamal.sahlsample.data.models.User
import com.kamal.sahlsample.data.models.request.LoginRequest
import com.kamal.sahlsample.data.repo.NotesRepository
import com.kamal.sahlsample.data.service.ResultEndPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LoginViewModel  @Inject constructor(private val notesRepository: NotesRepository, private val sharedPreferences: SharedPreferences, private val json: Json) : ViewModel() {


    private val _user = MutableLiveData<User>().apply {
        value = getUser()
    }
    val user: MutableLiveData<User> = _user
    private val _dataLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _dataLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun saveUser(user: User?) {
        sharedPreferences.edit().putString(K_USER, json.encodeToString(user)).apply()
    }

    fun getUser(): User? {
        return if (sharedPreferences.contains(K_USER)) json.decodeFromString<User>(
            sharedPreferences.getString(K_USER,"").toString()
        ) else null
    }


    fun login(email: String, password: String, lifecycleOwner: LifecycleOwner) {
        notesRepository.login(LoginRequest(email, password)).observe(lifecycleOwner) {
            when(it.status){
                ResultEndPoint.Status.SUCCESS-> {
                    it?.data?.data.let {
                        user.postValue(it)
                        saveUser(it)
                    }
                    it.data?.error?.let {
                        _errorMsg.value = it
                    }
                    _dataLoading.value = false
                }
                ResultEndPoint.Status.LOADING-> _dataLoading.value=true
                ResultEndPoint.Status.ERROR-> {
                    _dataLoading.value = false
                    _errorMsg.value = it.data?.error.toString()

                }
            }

        }

    }
}