package com.dionos.features.followed_stream_list.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import com.dionos.features.followed_stream_list.data.source.FeaturesDataSource
import com.dionos.user.data.repository.UserRepository
import javax.inject.Inject
import javax.inject.Named

class GetFollowedStreamListPagedUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @Named("FeaturesNetworkDataSource") private val featuresNetworkDataSource: FeaturesDataSource
) : PagingSource<String, FollowedStreamDto>() {

    private var prevKey: String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, FollowedStreamDto> {
        try {
            val userId = userRepository.getUserId()

            val response =
                featuresNetworkDataSource.getFollowedStreamList(
                    cursor = params.key,
                    loadSize = params.loadSize,
                    userId = userId
                )

            val cursor = response.pagination.cursor

            val result = LoadResult.Page(
                data = response.data,
                prevKey = prevKey,
                nextKey = cursor
            )

            prevKey = cursor

            return result
        } catch (throwable: Throwable) {
            return LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<String, FollowedStreamDto>): String? = prevKey
}