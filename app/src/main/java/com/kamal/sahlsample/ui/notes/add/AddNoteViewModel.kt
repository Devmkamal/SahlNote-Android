package com.kamal.sahlsample.ui.notes.add

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamal.sahlsample.data.repo.NotesRepository
import com.kamal.sahlsample.data.service.ResultEndPoint
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {


    private val _AddNote = MutableLiveData<Boolean>().apply {
        value = false
    }
    val addNote: MutableLiveData<Boolean> = _AddNote
    private val _dataLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _dataLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg



    fun addNote(note: String , lifecycleOwner: LifecycleOwner) {
        notesRepository.addNote(note).observe(lifecycleOwner) {
            when(it.status){
                ResultEndPoint.Status.SUCCESS-> {
                    it?.data?.data.let {
                        addNote.postValue(it)
                    }
                    it.data?.error?.let {
                        _errorMsg.value = it
                    }
                    _dataLoading.value = false
                }
                ResultEndPoint.Status.LOADING-> _dataLoading.value=true
                ResultEndPoint.Status.ERROR-> {
                    _dataLoading.value = false
                    _errorMsg.value = it.message.toString()

                }
            }

        }

    }
}