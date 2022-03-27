package com.dionos.features.followed_stream_list.data.service

import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApiService {

    @GET("/streams/followed")
    suspend fun getFollowedStreamList(
        @Query("user_id") userId: String,
        @Query("after") cursor: String?,
        @Query("first") quantity: Int = 20,
    ): FollowedStreamListResponse

}