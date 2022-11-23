package com.kamal.sahlsample.ui.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.kamal.sahlsample.common.ext.initSnackbar
import com.kamal.sahlsample.databinding.FragmentNoteBinding
import com.kamal.sahlsample.di.Injectable
import com.kamal.sahlsample.di.ViewModelFactory
import com.kamal.sahlsample.di.injectViewModel
import javax.inject.Inject

class NotesFragment : Fragment(), Injectable {

    private lateinit var notesViewModel: NotesViewModel
    private var binding: FragmentNoteBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val notesAdapter = NotesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel = injectViewModel(viewModelFactory)
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        context ?: return binding?.root
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel= notesViewModel
        binding?.rcAdapter= notesAdapter
        binding?.lifecycleOwner = this.viewLifecycleOwner
        view.initSnackbar(this,notesViewModel.errorMsg, Snackbar.LENGTH_SHORT)
        callBacks()
        getNotes()

    }

    private fun getNotes() {
        notesViewModel.notes(viewLifecycleOwner)
    }

    private fun callBacks() {
        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            notesAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}