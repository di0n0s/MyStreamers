package com.dionos.features.followed_stream_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dionos.features.followed_stream_list.data.repository.FeaturesRepository
import com.dionos.features.followed_stream_list.presentation.vo.FollowedStreamVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowedStreamListViewModel @Inject constructor(private val featuresRepository: FeaturesRepository) :
    ViewModel() {

    private var cursor: String? = null
    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)

    private val _followedStreamList =
        MutableStateFlow<GetFollowedStreamListState>(GetFollowedStreamListState.Idle)
    val followedStreamList: StateFlow<GetFollowedStreamListState>
        get() = _followedStreamList

    init {
        handleUserIntent()
    }

    private fun handleUserIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is UserIntent.GetFollowedStreamList -> getFollowedStreamList()
                }
            }

        }
    }

    private fun getFollowedStreamList() {
        viewModelScope.launch {
            _followedStreamList.value = GetFollowedStreamListState.Loading

            val dtoList = featuresRepository.getFollowedStreamList(cursor)

            cursor = dtoList.pagination.cursor

            //TODO extract this in another class
            val voList: List<FollowedStreamVO> =
                dtoList.data.map { followedStreamDto ->
                    FollowedStreamVO(
                        id = followedStreamDto.id,
                        imagePath = followedStreamDto.thumbnailUrl,
                        userName = followedStreamDto.userName,
                        category = followedStreamDto.gameName,
                        title = followedStreamDto.title,
                        viewers = followedStreamDto.viewerCount
                    )
                }

            _followedStreamList.value = GetFollowedStreamListState.Success(voList)
            //TODO unhappy path?
        }
    }
}

sealed class UserIntent {
    object GetFollowedStreamList : UserIntent()
}

sealed class GetFollowedStreamListState {
    object Idle : GetFollowedStreamListState()
    object Loading : GetFollowedStreamListState()
    data class Success(val list: List<FollowedStreamVO>) : GetFollowedStreamListState()
    data class Error(val error: String?) : GetFollowedStreamListState()
}