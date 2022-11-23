package com.kamal.sahlsample.common.ext

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

fun View.initSnackbar(
    lifecycleOwner: LifecycleOwner,
    error: LiveData<String>,
    timeLength: Int
) {
    error.observe(lifecycleOwner) { error ->
        error?.let {
            showSnackbar(it, timeLength)
        }
    }
}