package com.dionos.features.followed_channels.data.source

import com.dionos.features.followed_channels.data.response.FollowedChannelListResponse
import com.dionos.features.followed_channels.data.service.TwitchApiService
import javax.inject.Inject

class FeaturesNetworkDataSource @Inject constructor(private val apiService: TwitchApiService) {

    suspend fun getFollowedStreamList(
        userId: String,
        cursor: String
    ): FollowedChannelListResponse =
        apiService.getFollowedStreamList(userId = userId, cursor = cursor, quantity = 20)

}