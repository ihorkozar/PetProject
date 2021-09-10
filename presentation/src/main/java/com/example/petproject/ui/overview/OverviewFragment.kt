package com.example.petproject.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petproject.R
import com.example.petproject.databinding.FragmentOverviewBinding
import com.example.petproject.databinding.ViewItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private var fragmentOverviewBinding: FragmentOverviewBinding? = null
    private var viewModelAdapter: RedditAdapter? = null
    private val overviewViewModel by viewModels<OverviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        overviewViewModel.childrenList.observe(viewLifecycleOwner, { childrenList ->
            childrenList.apply {
                viewModelAdapter?.childrenList = childrenList
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)
        fragmentOverviewBinding = binding
        binding.viewModel = overviewViewModel
        viewModelAdapter = RedditAdapter(OnClickListener {
            overviewViewModel.displayDetail(it)
        })
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        overviewViewModel.navigateToSelected.observe(viewLifecycleOwner, {
            if (null != it) {
                val itemBinding = ViewItemBinding.inflate(inflater)
                val extras = FragmentNavigatorExtras(
                    itemBinding.image as View to getString(R.string.transition_name)
                )
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionNavGalleryToDetailFragment(it.postData.thumbnail),
                    extras
                )
                overviewViewModel.displayDetailComplete()
            }
        })
        return binding.root
    }
}