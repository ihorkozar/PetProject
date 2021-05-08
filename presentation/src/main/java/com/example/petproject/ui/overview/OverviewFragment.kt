package com.example.petproject.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petproject.R
import com.example.petproject.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private var viewModelAdapter: RedditAdapter? = null
    private val viewModel: OverviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.childrenList.observe(viewLifecycleOwner, { childrenList ->
            childrenList?.apply {
                viewModelAdapter?.childrenList = childrenList// Why size = 1 ?????
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )
        binding.viewModel = viewModel
        viewModelAdapter = RedditAdapter(OnClickListener {
            viewModel.displayDetail(it)
        })
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        return binding.root
    }

}