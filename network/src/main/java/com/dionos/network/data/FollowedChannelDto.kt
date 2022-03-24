package com.dionos.network.data

import com.google.gson.annotations.SerializedName

data class FollowedChannelDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("user_name") val userName: String,
    @SerializedName("game_name") val gameName: String,
    @SerializedName("viewer_count") val viewerCount: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
)