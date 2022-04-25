package com.dionos.features.followed_stream_list.presentation.viewmodel

import android.app.Application
import androidx.paging.*
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import com.dionos.features.followed_stream_list.domain.GetFollowedStreamListPagedUseCase
import com.dionos.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FollowedStreamListViewModelTest {

    private lateinit var viewModel: FollowedStreamListViewModel

    @Mock
    private lateinit var useCase: GetFollowedStreamListPagedUseCase

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var params: PagingSource.LoadParams<String>

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        viewModel = FollowedStreamListViewModel(useCase, application)
    }

    private val followedStreamDto = FollowedStreamDto(
        id = "45274130444",
        gameName = "League of Legends",
        thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_iamcristinini-{width}x{height}.jpg",
        title = "\uD83D\uDD34 TU DUO FAVORITO | !prime !sub | Follow @IamCristinini",
        userName = "IamCristinini",
        viewerCount = 5789
    )

    private val list = listOf(followedStreamDto)


    @Test
    fun `WHEN pagingDataFlow is called THEN return a pagingDataFlow`() = runBlockingTest {
        //TODO this test is broken turbine is not working right when PagingData<T>.collectData() is called.
        // Trying to find a solution

//        val loadResuult = PagingSource.LoadResult.Page(
//            data = list,
//            prevKey = "prevKey",
//            nextKey = "nextKey"
//        )
//        Mockito.`when`(useCase.load(params)).thenReturn(loadResuult)
//
//        viewModel.pagingDataFlow.test {
//            val collectedData = expectMostRecentItem().collectData()
//            assertEquals(loadResuult, collectedData)
//            awaitComplete()
//        }
    }

    private suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }


}
