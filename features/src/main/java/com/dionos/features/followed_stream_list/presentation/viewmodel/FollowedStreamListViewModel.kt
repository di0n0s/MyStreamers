package com.dionos.features.followed_stream_list.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
    private val useCase: GetFollowedStreamListPagedUseCase,
    application: Application
) : AndroidViewModel(application) {

    val pagingDataFlow: Flow<PagingData<FollowedStreamVO>> by lazy { return@lazy getFollowedStreamListFlow() }

    private fun getFollowedStreamListFlow(): Flow<PagingData<FollowedStreamVO>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { useCase })

        return pager.flow.map { pagingData ->
            pagingData.map { followedStreamDto ->
                FollowedStreamVO.fromFollowedStreamDto(getApplication(), followedStreamDto)
            }
        }.cachedIn(viewModelScope)
    }
}