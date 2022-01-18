package com.jh.watch2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jh.watch2.databinding.ActivityMainItemBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val list = arrayListOf(1,2,3,4,5,6,7,8,9,10)

    class MainViewHolder(private val binding: ActivityMainItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Int) {
            binding.tv.text = "아이템$item"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}