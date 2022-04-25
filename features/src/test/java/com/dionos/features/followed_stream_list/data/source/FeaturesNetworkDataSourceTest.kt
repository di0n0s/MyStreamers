package com.dionos.features.followed_stream_list.data.source

import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import com.dionos.features.followed_stream_list.data.service.TwitchApiService
import com.dionos.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
class FeaturesNetworkDataSourceTest {

    private lateinit var dataSource: FeaturesNetworkDataSource

    private val ioDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var apiService: TwitchApiService

    @Mock
    private lateinit var response: FollowedStreamListResponse

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        dataSource = FeaturesNetworkDataSource(ioDispatcher = ioDispatcher, apiService = apiService)
    }

    @Test
    fun `GIVEN a cursor, a loadSize and userID WHEN getFollowedStreamList function is called THEN return a FollowedStreamListResponse`() =
        runBlockingTest {
            //GIVEN
            val cursor = "cursor"
            val loadSize = 20
            val userId = "userId"

            `when`(
                apiService.getFollowedStreamList(
                    cursor = cursor,
                    loadSize = loadSize,
                    userId = userId
                )
            ).thenReturn(response)

            //WHEN
            val result =
                dataSource.getFollowedStreamList(
                    cursor = "cursor",
                    loadSize = 20,
                    userId = "userId"
                )

            //THEN
            assertEquals(
                response,
                result
            )
            verify(apiService).getFollowedStreamList(
                cursor = cursor,
                loadSize = loadSize,
                userId = userId
            )

        }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(apiService, response)
    }
}