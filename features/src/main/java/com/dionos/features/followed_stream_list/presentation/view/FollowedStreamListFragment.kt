package com.dionos.features.followed_stream_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.dionos.features.databinding.FragmentFollowedStreamListBinding
import com.dionos.features.followed_stream_list.presentation.adapter.FollowedStreamListAdapter
import com.dionos.features.followed_stream_list.presentation.adapter.FollowedStreamLoadStateAdapter
import com.dionos.features.followed_stream_list.presentation.viewmodel.FollowedStreamListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowedStreamListFragment : Fragment() {

    //Views
    private var _binding: FragmentFollowedStreamListBinding? = null
    private val binding get() = _binding

    //Adapter
    private var adapter: FollowedStreamListAdapter? = null

    //ViewModel
    private val viewModel: FollowedStreamListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onInitView(inflater, container)
        setToolbar()
        setObserver()
        return binding?.root
    }

    private fun onInitView(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentFollowedStreamListBinding.inflate(inflater, container, false)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = FollowedStreamListAdapter()

        binding?.recyclerView?.adapter =
            adapter?.withLoadStateFooter(footer = FollowedStreamLoadStateAdapter { adapter?.retry() })

        adapter?.addLoadStateListener { loadState -> manageUiVisibility(loadState) }

        binding?.retryButton?.setOnClickListener { adapter?.retry() }
    }

    private fun manageUiVisibility(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter?.itemCount == 0

        binding?.let {
            it.emptyTextView.isVisible = isListEmpty

            // Only shows the list if refresh succeeds or list not empty
            it.recyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading || !isListEmpty
            // Show loading during initial load or refresh.
            it.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            it.retryButton.isVisible = loadState.source.refresh is LoadState.Error
        }

    }


    private fun setToolbar() {
        (activity as AppCompatActivity?)?.apply {
            setSupportActionBar(binding?.toolbar)
            supportActionBar?.title = "Followed Stream List"
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }
}