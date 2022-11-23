package com.kamal.sahlsample.ui.notes.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kamal.sahlsample.common.ext.initSnackbar
import com.kamal.sahlsample.databinding.FragmentAddNotesBinding
import com.kamal.sahlsample.di.Injectable
import com.kamal.sahlsample.di.ViewModelFactory
import com.kamal.sahlsample.di.injectViewModel
import javax.inject.Inject

class AddNoteFragment : Fragment() , Injectable {

    private lateinit var addNoteViewModel: AddNoteViewModel
    private var binding: FragmentAddNotesBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteViewModel = injectViewModel(viewModelFactory)
        binding = FragmentAddNotesBinding.inflate(inflater, container, false)
        context ?: return binding?.root
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel= addNoteViewModel
        binding?.lifecycleOwner = this.viewLifecycleOwner
        view.initSnackbar(this,addNoteViewModel.errorMsg, Snackbar.LENGTH_SHORT)
        callBacks()
        viewsInterAction()


    }

    private fun callBacks() {
        addNoteViewModel.addNote.observe(viewLifecycleOwner, Observer {
            if (it)
            findNavController().popBackStack()
        })
    }

    private fun viewsInterAction() {
        binding?.btnAdd?.setOnClickListener {
            addNoteViewModel.addNote(binding?.etNote?.text.toString(), viewLifecycleOwner)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}