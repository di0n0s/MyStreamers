package com.dionos.features.followed_stream_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dionos.features.databinding.FragmentFollowedStreamListBinding
import com.dionos.features.followed_stream_list.presentation.adapter.FollowedStreamListAdapter
import com.dionos.features.followed_stream_list.presentation.viewmodel.FollowedStreamListViewModel
import com.dionos.features.followed_stream_list.presentation.viewmodel.GetFollowedStreamListState
import com.dionos.features.followed_stream_list.presentation.viewmodel.UserIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

        binding?.recyclerView?.adapter = adapter//?.withLoadStateFooter(footer = )

        //adapter?.addLoadStateListener {  }
    }


    private fun setToolbar() {
        (activity as AppCompatActivity?)?.apply {
            setSupportActionBar(binding?.toolbar)
            supportActionBar?.title = "Followed Stream List"
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.userIntent.send(UserIntent.GetFollowedStreamList)

            viewModel.followedStreamList.collect { state ->
                when (state) {
                    is GetFollowedStreamListState.Error -> TODO()
                    GetFollowedStreamListState.Idle -> {}
                    GetFollowedStreamListState.Loading -> TODO()
                    is GetFollowedStreamListState.Success -> {
                        state.flow.collectLatest { pagingData ->
                            adapter?.submitData(pagingData)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}