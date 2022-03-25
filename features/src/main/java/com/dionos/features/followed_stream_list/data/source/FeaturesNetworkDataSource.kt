package com.dionos.features.followed_stream_list.data.source

import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import com.dionos.features.followed_stream_list.data.service.TwitchApiService
import javax.inject.Inject

class FeaturesNetworkDataSource @Inject constructor(private val apiService: TwitchApiService) {

    suspend fun getFollowedStreamList(
        userId: String,
        cursor: String
    ): FollowedStreamListResponse =
        apiService.getFollowedStreamList(userId = userId, cursor = cursor, quantity = 20)

}