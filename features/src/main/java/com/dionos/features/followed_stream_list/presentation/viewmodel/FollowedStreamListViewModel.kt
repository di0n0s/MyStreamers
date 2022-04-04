package com.dionos.features.followed_stream_list.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dionos.features.followed_stream_list.domain.GetFollowedStreamListPagedUseCase
import com.dionos.features.followed_stream_list.presentation.vo.FollowedStreamVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class FollowedStreamListViewModel @Inject constructor(
    private val useCase: GetFollowedStreamListPagedUseCase
) : ViewModel() {

    val pagingDataFlow by lazy { return@lazy getFollowedStreamListFlow() }

    private fun getFollowedStreamListFlow(): Flow<PagingData<FollowedStreamVO>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { useCase })

        val widthPixels = Resources.getSystem().displayMetrics.widthPixels

        val heightPixels = (widthPixels / 16) * 10

        return pager.flow.map { pagingData ->
            pagingData.map { followedStreamDto ->
                //TODO extract this in another class
                FollowedStreamVO(
                    id = followedStreamDto.id,
                    imagePath = """${followedStreamDto.thumbnailUrl.substringBeforeLast("-")}-${widthPixels}x$heightPixels.jpg""",
                    userName = followedStreamDto.userName,
                    category = followedStreamDto.gameName,
                    title = followedStreamDto.title,
                    viewers = followedStreamDto.viewerCount
                )
            }
        }.cachedIn(viewModelScope)
    }
}