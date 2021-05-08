package com.example.petproject.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
                viewModelAdapter?.childrenList = childrenList
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        binding.viewModel = viewModel
        viewModelAdapter = RedditAdapter(OnClickListener {
            viewModel.displayDetail(it)
        })
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        viewModel.navigateToSelected.observe(viewLifecycleOwner,{
            if (null != it){
                this.findNavController().navigate(OverviewFragmentDirections.actionNavGalleryToDetailFragment(it.postData.thumbnail))
                viewModel.displayDetailComplete()
            }
        })
        return binding.root
    }

}