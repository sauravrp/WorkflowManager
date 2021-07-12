package com.sauravrp.takehome.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sauravrp.takehome.Work
import com.sauravrp.takehome.databinding.WorkItemBinding

class WorkAdapter : ListAdapter<Work, WorkAdapter.WorkViewHolder>(WorkListDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        val binding = WorkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return WorkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class WorkViewHolder(private val binding: WorkItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(work: Work) {
            binding.work.text = work.data
        }
    }
}

class WorkListDiffer : DiffUtil.ItemCallback<Work>() {
    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean = oldItem == newItem
}