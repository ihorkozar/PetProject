package com.example.petproject.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.petproject.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {
    private var fragmentSlideshowBinding: FragmentSlideshowBinding? = null
    private val slideshowViewModel by viewModels<SlideshowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        fragmentSlideshowBinding = binding
        binding.viewModel = slideshowViewModel
        slideshowViewModel.text.observe(viewLifecycleOwner, {
            binding.textSlideshow.text = it
        })
        return binding.root
    }

    override fun onDestroyView() {
        fragmentSlideshowBinding = null
        super.onDestroyView()
    }
}