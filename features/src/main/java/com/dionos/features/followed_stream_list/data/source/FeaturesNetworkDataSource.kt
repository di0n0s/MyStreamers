package com.dionos.features.followed_stream_list.data.source

import com.dionos.core.di.IoDispatcher
import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import com.dionos.features.followed_stream_list.data.service.TwitchApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeaturesNetworkDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiService: TwitchApiService
) {

    suspend fun getFollowedStreamList(
        userId: String,
        cursor: String?,
        loadSize: Int
    ): FollowedStreamListResponse = withContext(ioDispatcher) {
        apiService.getFollowedStreamList(
            userId = userId,
            cursor = cursor,
            loadSize
        )
    }


}