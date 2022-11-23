package com.kamal.sahlsample.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kamal.sahlsample.common.ext.initSnackbar
import com.kamal.sahlsample.databinding.FragmentRegisterBinding
import com.kamal.sahlsample.di.Injectable
import com.kamal.sahlsample.di.ViewModelFactory
import com.kamal.sahlsample.di.injectViewModel
import com.kamal.sahlsample.ui.login.LoginFragmentDirections
import javax.inject.Inject

class RegisterFragment : Fragment() , Injectable {

    private var binding: FragmentRegisterBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        context ?: return binding?.root
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel= viewModel
        binding?.lifecycleOwner = this.viewLifecycleOwner
        view.initSnackbar(this,viewModel.errorMsg, Snackbar.LENGTH_SHORT)
        viewsInterAction()
        callBacks()


    }

    private fun viewsInterAction() {
        binding?.btnRegister?.setOnClickListener {
            register()
        }
    }



    private fun callBacks() {
        viewModel.user.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavNotes())
            }
        }
    }

    fun register(){
        viewModel.register(binding?.etName?.text.toString() , binding?.etMobile?.text.toString(), binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString(), viewLifecycleOwner)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}