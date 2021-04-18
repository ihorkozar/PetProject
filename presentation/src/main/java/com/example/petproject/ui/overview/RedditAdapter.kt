package com.example.petproject.ui.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.databinding.ViewItemBinding
import com.example.petproject.domain.Models

class RedditAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Models.Children, RedditAdapter.RedditViewHolder>(DiffCallback) {

    class RedditViewHolder(private var binding: ViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(children: Models.Children) {
            binding.title.text = children.post.title
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Models.Children>() {
        override fun areItemsTheSame(oldItem: Models.Children, newItem: Models.Children): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Models.Children, newItem: Models.Children): Boolean {
            return oldItem.post.id == newItem.post.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        return RedditViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        val children = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(children)
        }
        holder.bind(children)
    }

    class OnClickListener(val clickListener: (children: Models.Children) -> Unit) {
        fun onClick(children: Models.Children) = clickListener(children)
    }

}

