package com.kamal.sahlsample.data.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object DataBinderUtils {

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: androidx.recyclerview.widget.ListAdapter<Any,RecyclerView.ViewHolder>) {
        recyclerView.adapter  = adapter
    }
    
}