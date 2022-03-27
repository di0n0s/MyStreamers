package com.dionos.features.followed_stream_list.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dionos.features.R
import com.dionos.features.databinding.ChannelItemBinding
import com.dionos.features.followed_stream_list.presentation.vo.FollowedStreamVO

class FollowedStreamViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.channel_item, parent, false
    )
) {

    private val binding = ChannelItemBinding.bind(itemView)

    fun bind(followedStreamVO: FollowedStreamVO?) {
        with(binding) {
            Glide.with(itemView)
                .load(followedStreamVO?.imagePath)
                .into(thumbnailImageView)
            viewersTextView.text = followedStreamVO?.viewers
            streamTitleTextView.text = followedStreamVO?.title
            channelNameTextView.text = followedStreamVO?.userName
            categoryNameTextView.text = followedStreamVO?.category
        }

    }
}