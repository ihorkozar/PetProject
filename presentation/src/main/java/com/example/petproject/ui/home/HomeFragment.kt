package com.example.petproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petproject.R
import com.example.petproject.databinding.FragmentHomeBinding
import com.example.petproject.databinding.FragmentOverviewBinding
import com.example.petproject.ui.overview.OverviewViewModel
import com.example.petproject.ui.overview.RedditAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = homeViewModel
        homeViewModel.text.observe(viewLifecycleOwner, {
            binding.textHome.text = it
        })
        return binding.root
    }
}