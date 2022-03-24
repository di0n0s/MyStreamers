package com.dionos.network.data

import com.google.gson.annotations.SerializedName

data class FollowedChannelsResponse(
    @SerializedName("data") val data: List<FollowedChannelDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)