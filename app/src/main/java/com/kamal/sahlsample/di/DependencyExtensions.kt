package com.kamal.sahlsample.di

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this.viewModelStore, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModelForChild(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(requireParentFragment().requireParentFragment(), viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> DialogFragment.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this.viewModelStore, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, viewModelFactory).get(T::class.java)
}