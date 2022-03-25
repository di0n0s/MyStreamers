package com.dionos.features.followed_stream_list.data.response

import com.dionos.features.followed_stream_list.data.response.dto.FollowedChannelDto
import com.dionos.features.followed_stream_list.data.response.dto.PaginationDto
import com.google.gson.annotations.SerializedName

data class FollowedStreamListResponse(
    @SerializedName("data") val data: List<FollowedChannelDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)