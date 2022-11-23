package com.kamal.sahlsample.ui.notes.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamal.sahlsample.data.models.Note
import com.kamal.sahlsample.data.repo.NotesRepository
import com.kamal.sahlsample.data.service.ResultEndPoint
import javax.inject.Inject

class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {


    private val _notes = MutableLiveData<List<Note>>().apply {
        mutableListOf<Note>()
    }
    val notes: MutableLiveData<List<Note>> = _notes
    private val _dataLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _dataLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg



    fun notes(lifecycleOwner: LifecycleOwner) {
        notesRepository.notes().observe(lifecycleOwner) {
            when(it.status){
                ResultEndPoint.Status.SUCCESS-> {
                    it?.data?.data.let {
                        _notes.postValue(it)
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