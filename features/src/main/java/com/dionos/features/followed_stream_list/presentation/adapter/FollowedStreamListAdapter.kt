package com.dionos.features.followed_stream_list.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dionos.features.followed_stream_list.presentation.view.FollowedStreamViewHolder
import com.dionos.features.followed_stream_list.presentation.vo.FollowedStreamVO

class FollowedStreamListAdapter : PagingDataAdapter<FollowedStreamVO, FollowedStreamViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FollowedStreamVO>() {
            override fun areItemsTheSame(
                oldItem: FollowedStreamVO,
                newItem: FollowedStreamVO
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: FollowedStreamVO,
                newItem: FollowedStreamVO
            ): Boolean = oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: FollowedStreamViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedStreamViewHolder =
        FollowedStreamViewHolder(parent)
}