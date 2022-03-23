package com.dionos.features.followed_channels.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dionos.features.R
import com.dionos.features.databinding.ChannelItemBinding

class FollowedChannelViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.channel_item, parent, false
    )
) {

    private val binding = ChannelItemBinding.bind(itemView)

    fun bind(followedChannelVO: FollowedChannelVO?) {
        with(binding) {
            Glide.with(itemView)
                .load(followedChannelVO?.imagePath)
                .into(thumbnailImageView)
            viewersTextView.text = followedChannelVO?.viewers
            streamTitleTextView.text = followedChannelVO?.title
            channelNameTextView.text = followedChannelVO?.name
            categoryNameTextView.text = followedChannelVO?.category
        }

    }
}