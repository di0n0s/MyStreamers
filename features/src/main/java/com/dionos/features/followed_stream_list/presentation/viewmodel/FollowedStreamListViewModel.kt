package com.dionos.features.followed_stream_list.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dionos.features.followed_stream_list.domain.GetFollowedStreamListPagedUseCase
import com.dionos.features.followed_stream_list.presentation.vo.FollowedStreamVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FollowedStreamListViewModel @Inject constructor(
    private val useCase: GetFollowedStreamListPagedUseCase
) : ViewModel() {

    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)

    private val _followedStreamList =
        MutableStateFlow<GetFollowedStreamListFirstPageState>(GetFollowedStreamListFirstPageState.Idle)
    val followedStreamList: StateFlow<GetFollowedStreamListFirstPageState>
        get() = _followedStreamList

    init {
        handleUserIntent()
    }

    private fun handleUserIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is UserIntent.GetFollowedStreamListFirstPage -> getFollowedStreamListFirstPage()
                }
            }

        }
    }

    private fun getFollowedStreamListFirstPage() {
        viewModelScope.launch {
            _followedStreamList.value = GetFollowedStreamListFirstPageState.Loading

            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { useCase })

            val widthPixels = Resources.getSystem().displayMetrics.widthPixels

            val heightPixels = (widthPixels / 16) * 10

            val pagerMap = pager.flow.map { pagingData ->
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
            }.cachedIn(this)

            pagerMap.collectLatest { pagingData ->
                _followedStreamList.value = GetFollowedStreamListFirstPageState.Success(pagingData)
            }
        }
    }
}

sealed class UserIntent {
    object GetFollowedStreamListFirstPage : UserIntent()
}

sealed class GetFollowedStreamListFirstPageState {
    object Idle : GetFollowedStreamListFirstPageState()
    object Loading : GetFollowedStreamListFirstPageState()
    data class Success(val pagingData: PagingData<FollowedStreamVO>) :
        GetFollowedStreamListFirstPageState()
}