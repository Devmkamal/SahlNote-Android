package com.kamal.sahlsample

import androidx.lifecycle.ViewModel
import com.kamal.sahlsample.data.repo.NotesRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel()