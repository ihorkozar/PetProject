package com.example.petproject.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.databinding.ViewItemBinding
import com.example.petproject.domain.Models

class RedditAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RedditViewHolder>() {

    var childrenList: List<Models.Children> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val withBinding: ViewItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            RedditViewHolder.LAYOUT,
            parent,
            false
        )
        return RedditViewHolder(withBinding)
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener.onClick(childrenList[position])
        }
        holder.binding.also {
            it.children = childrenList[position]
        }
    }

    override fun getItemCount(): Int = childrenList.size
}

class OnClickListener(val clickListener: (children: Models.Children) -> Unit) {
    fun onClick(children: Models.Children) = clickListener(children)
}

class RedditViewHolder(val binding: ViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object{
        @LayoutRes
        val LAYOUT = R.layout.view_item
    }
}

