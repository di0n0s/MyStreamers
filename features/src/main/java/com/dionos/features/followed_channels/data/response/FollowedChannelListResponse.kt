package com.dionos.features.followed_channels.data.response

import com.dionos.features.followed_channels.data.response.dto.FollowedChannelDto
import com.dionos.features.followed_channels.data.response.dto.PaginationDto
import com.google.gson.annotations.SerializedName

data class FollowedChannelListResponse(
    @SerializedName("data") val data: List<FollowedChannelDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)