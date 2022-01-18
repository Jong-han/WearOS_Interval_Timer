package com.jh.watch2.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jh.watch2.TimerModel
import com.jh.watch2.databinding.ActivityMainItemBinding

class MainAdapter(private val onClickItem: (TimerModel)->Unit): ListAdapter<TimerModel, MainAdapter.MainViewHolder>(MainDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position], onClickItem)
    }

    class MainViewHolder(private val binding: ActivityMainItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TimerModel, onClickItem: (TimerModel)->Unit) {
            binding.data = item
            binding.parent.setOnClickListener {
                onClickItem.invoke(item)
            }
        }
    }

    class MainDiffUtil: DiffUtil.ItemCallback<TimerModel>() {

        override fun areItemsTheSame(oldItem: TimerModel, newItem: TimerModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TimerModel, newItem: TimerModel): Boolean {
            return oldItem == newItem
        }

    }

}