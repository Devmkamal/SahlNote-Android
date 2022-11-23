package com.kamal.sahlsample.ui.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kamal.sahlsample.data.models.Note
import com.kamal.sahlsample.databinding.RvNotesItemBinding


class NotesAdapter : ListAdapter<Note, NotesAdapter.ViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = RvNotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        holder.binding.note = model.text
    }

    class ViewHolder(itemBinding: RvNotesItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: RvNotesItemBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem == newItem
        }
    }
}
