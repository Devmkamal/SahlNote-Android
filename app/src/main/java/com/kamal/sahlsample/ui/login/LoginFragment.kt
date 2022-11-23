package com.kamal.sahlsample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kamal.sahlsample.common.ext.initSnackbar
import com.kamal.sahlsample.databinding.FragmentLoginBinding
import com.kamal.sahlsample.di.Injectable
import com.kamal.sahlsample.di.ViewModelFactory
import com.kamal.sahlsample.di.injectViewModel
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = injectViewModel(viewModelFactory)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        context ?: return binding?.root
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel= loginViewModel
        binding?.lifecycleOwner = this.viewLifecycleOwner
        view.initSnackbar(this,loginViewModel.errorMsg,Snackbar.LENGTH_SHORT)
        viewsInterAction()
        callBacks()


    }

    private fun viewsInterAction() {
        binding?.btnLogin?.setOnClickListener {
            login()
        }
    }



    private fun callBacks() {
        loginViewModel.user.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavNotes())
            }
        }
    }

    fun login(){
        loginViewModel.login(binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString(), viewLifecycleOwner)
    }
    override fun onDestroyView() {
        super.onDestroyView()
       binding = null
    }
}