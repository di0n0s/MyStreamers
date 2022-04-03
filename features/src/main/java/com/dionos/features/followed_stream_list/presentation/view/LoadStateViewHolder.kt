package com.dionos.features.followed_stream_list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.dionos.features.R
import com.dionos.features.databinding.ItemLoadStateFooterBinding

class LoadStateViewHolder(parent: ViewGroup, private val retry: () -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_load_state_footer, parent, false
        )
    ) {

    private val binding = ItemLoadStateFooterBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        binding.retryButton.setOnClickListener { retry.invoke() }

        if (loadState is LoadState.Error) {
            binding.errorTextView.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorTextView.isVisible = loadState is LoadState.Error
    }


}