package com.example.petproject.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.databinding.FragmentOverviewBinding
import com.example.petproject.domain.Models

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private val overviewViewModel: OverviewViewModel by lazy{
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        binding.viewModel = overviewViewModel
        //bindRecyclerView(binding.recycler, overviewViewModel.childrenList.value)
        binding.recycler.adapter = RedditAdapter(RedditAdapter.OnClickListener{
            overviewViewModel.displayDetail(it)
        })
        /*overviewViewModel.navigateToSelectedProperty.observe(this, {
            if (null != it){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })*/
        return binding.root
    }

    /*//bindRecyclerView
    private fun bindRecyclerView(recyclerView: RecyclerView, data: List<Models.Children>?) {
        val adapter = recyclerView.adapter as RedditAdapter
        adapter.submitList(data)
    }*/
}