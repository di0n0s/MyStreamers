package com.dionos.features.followed_channels.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dionos.features.databinding.ChannelItemBinding

class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ChannelItemBinding.bind(itemView)

    fun bind(channelVO: ChannelVO) {
        with(binding) {
            Glide.with(itemView)
                .load(channelVO.imagePath)
                .into(thumbnailImageView)
            viewersTextView.text = channelVO.viewers
            streamTitleTextView.text = channelVO.title
            channelNameTextView.text = channelVO.name
            categoryNameTextView.text = channelVO.category
        }

    }
}