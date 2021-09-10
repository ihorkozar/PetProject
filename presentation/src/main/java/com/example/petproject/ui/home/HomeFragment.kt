package com.example.petproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.petproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentHomeBinding = binding
        binding.viewModel = homeViewModel
        homeViewModel.text.observe(viewLifecycleOwner, {
            binding.textHome.text = it
        })
        return binding.root
    }

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}