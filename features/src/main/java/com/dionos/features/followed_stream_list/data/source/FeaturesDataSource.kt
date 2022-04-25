package com.dionos.features.followed_stream_list.data.source

import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse

interface FeaturesDataSource {

    suspend fun getFollowedStreamList(
        userId: String,
        cursor: String?,
        loadSize: Int
    ): FollowedStreamListResponse

}