package com.dionos.features.followed_channels.data.repository

import com.dionos.features.followed_channels.data.source.FeaturesNetworkDataSource
import com.dionos.user.data.source.UserNetworkDataSource
import javax.inject.Inject

class FeaturesRepository @Inject constructor(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val featuresNetworkDataSource: FeaturesNetworkDataSource
) {

    suspend fun getFollowedStreamList(cursor: String) {
        val userId = userNetworkDataSource.getUserId()
        featuresNetworkDataSource.getFollowedStreamList(userId = userId, cursor = cursor)
    }
}