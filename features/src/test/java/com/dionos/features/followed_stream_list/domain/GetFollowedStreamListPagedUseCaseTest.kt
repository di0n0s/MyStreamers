package com.dionos.features.followed_stream_list.domain

import androidx.paging.PagingSource
import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import com.dionos.features.followed_stream_list.data.response.dto.PaginationDto
import com.dionos.features.followed_stream_list.data.source.FeaturesNetworkDataSource
import com.dionos.test_utils.MainCoroutineRule
import com.dionos.user.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetFollowedStreamListPagedUseCaseTest {

    private lateinit var useCase: GetFollowedStreamListPagedUseCase

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var featuresNetworkDataSource: FeaturesNetworkDataSource

    @Mock
    private lateinit var response: FollowedStreamListResponse

    @Mock
    private lateinit var followedStreamDtoList: List<FollowedStreamDto>

    @Mock
    private lateinit var pagination: PaginationDto

    @Mock
    private lateinit var throwable: RuntimeException

    private val key = "key"

    private val cursor = "cursor"

    private val userId = "userId"

    private val params: PagingSource.LoadParams<String> =
        PagingSource.LoadParams.Refresh(key = key, loadSize = 20, placeholdersEnabled = false)

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        useCase = GetFollowedStreamListPagedUseCase(userRepository, featuresNetworkDataSource)
    }

    @Test
    fun `WHEN load function is called THEN return a LoadResultPage`() =
        runBlockingTest {
            //GIVEN
            `when`(userRepository.getUserId()).thenReturn(userId)
            `when`(
                featuresNetworkDataSource.getFollowedStreamList(
                    userId = userId,
                    cursor = params.key,
                    loadSize = params.loadSize
                )
            ).thenReturn(response)
            `when`(response.data).thenReturn(followedStreamDtoList)
            `when`(response.pagination).thenReturn(pagination)
            `when`(pagination.cursor).thenReturn(cursor)

            //WHEN
            val result = useCase.load(params)

            //THEN
            val page = PagingSource.LoadResult.Page(
                data = followedStreamDtoList,
                prevKey = null,
                nextKey = cursor
            )
            assertEquals(page, result)

            verify(userRepository).getUserId()
            verify(featuresNetworkDataSource).getFollowedStreamList(
                userId = userId,
                cursor = params.key,
                loadSize = params.loadSize
            )
            verify(response).data
            verify(response).pagination
            verify(pagination).cursor

        }

    @Test
    fun `GIVEN a Throwable WHEN getUserId function is called THEN return a LoadResultError`() =
        runBlockingTest {
            //GIVEN
            `when`(userRepository.getUserId()).thenThrow(throwable)

            //WHEN
            val result = useCase.load(params)

            //THEN
            val page = PagingSource.LoadResult.Error<String, FollowedStreamDto>(
                throwable = throwable
            )
            assertEquals(page, result)

            verify(userRepository).getUserId()

        }

    @Test
    fun `GIVEN a Throwable WHEN getFollowedStreamList function is called THEN return a LoadResultError`() =
        runBlockingTest {
            //GIVEN
            `when`(userRepository.getUserId()).thenReturn(userId)
            `when`(
                featuresNetworkDataSource.getFollowedStreamList(
                    userId = userId,
                    cursor = params.key,
                    loadSize = params.loadSize
                )
            ).thenThrow(throwable)

            //WHEN
            val result = useCase.load(params)

            //THEN
            val page = PagingSource.LoadResult.Error<String, FollowedStreamDto>(
                throwable = throwable
            )
            assertEquals(page, result)

            verify(userRepository).getUserId()
            verify(featuresNetworkDataSource).getFollowedStreamList(
                userId = userId,
                cursor = params.key,
                loadSize = params.loadSize
            )
        }


    @After
    fun tearDown() {
        verifyNoMoreInteractions(
            userRepository,
            featuresNetworkDataSource,
            response,
            followedStreamDtoList,
            pagination,
            throwable
        )
    }
}