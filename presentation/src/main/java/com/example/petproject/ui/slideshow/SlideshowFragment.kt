package com.example.petproject.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.petproject.R
import com.example.petproject.databinding.FragmentHomeBinding
import com.example.petproject.databinding.FragmentSlideshowBinding
import com.example.petproject.ui.home.HomeViewModel

class SlideshowFragment : Fragment() {
    private lateinit var binding: FragmentSlideshowBinding
    private val slideshowViewModel by viewModels<SlideshowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlideshowBinding.inflate(inflater)
        binding.viewModel = slideshowViewModel
        slideshowViewModel.text.observe(viewLifecycleOwner, {
            binding.textSlideshow.text = it
        })
        return binding.root
    }
}