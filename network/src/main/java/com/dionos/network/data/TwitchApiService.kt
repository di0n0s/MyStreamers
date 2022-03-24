package com.dionos.network.data

import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApiService {

    @GET("/streams/followed")
    suspend fun getFollowedChannels(
        @Query("user_id") userId: String,
        @Query("after") after: String,
        @Query("first") first: Int,
    ): FollowedChannelsResponse

}