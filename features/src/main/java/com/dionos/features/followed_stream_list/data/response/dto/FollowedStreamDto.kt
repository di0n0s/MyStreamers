package com.dionos.features.followed_stream_list.data.response.dto

import com.google.gson.annotations.SerializedName

data class FollowedStreamDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("user_name") val userName: String,
    @SerializedName("game_name") val gameName: String,
    @SerializedName("viewer_count") val viewerCount: Int,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
)