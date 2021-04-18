package com.example.petproject

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.domain.Models
import com.example.petproject.ui.overview.RedditAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Models.Children>?) {
    val adapter = recyclerView.adapter as RedditAdapter
    adapter.submitList(data)
}