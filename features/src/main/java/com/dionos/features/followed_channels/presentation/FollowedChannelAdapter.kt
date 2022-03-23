package com.dionos.features.followed_channels.presentation

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class FollowedChannelAdapter : PagingDataAdapter<FollowedChannelVO, FollowedChannelViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FollowedChannelVO>() {
            override fun areItemsTheSame(
                oldItem: FollowedChannelVO,
                newItem: FollowedChannelVO
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: FollowedChannelVO,
                newItem: FollowedChannelVO
            ): Boolean = oldItem == newItem


        }
    }

    override fun onBindViewHolder(holder: FollowedChannelViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedChannelViewHolder =
        FollowedChannelViewHolder(parent)
}