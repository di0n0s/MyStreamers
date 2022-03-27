package com.dionos.features.followed_stream_list.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dionos.features.followed_stream_list.data.repository.FeaturesRepository
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import javax.inject.Inject

class GetFollowedStreamListPagedUseCase @Inject constructor(private val repository: FeaturesRepository) :
    PagingSource<String, FollowedStreamDto>() {

    private var prevKey: String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, FollowedStreamDto> {
        val response =
            repository.getFollowedStreamList(cursor = params.key, loadSize = params.loadSize)

        val result = LoadResult.Page(
            data = response.data,
            prevKey = prevKey,
            nextKey = response.pagination.cursor
        )

        prevKey = response.pagination.cursor

        return result
    }

    override fun getRefreshKey(state: PagingState<String, FollowedStreamDto>): String? = prevKey
}