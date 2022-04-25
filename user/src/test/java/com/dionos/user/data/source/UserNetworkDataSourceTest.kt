package com.dionos.user.data.source

import com.dionos.test_utils.MainCoroutineRule
import com.dionos.user.data.response.UserResponse
import com.dionos.user.data.response.dto.UserDto
import com.dionos.user.data.service.UserApiService
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
class UserNetworkDataSourceTest {

    private lateinit var dataSource: UserNetworkDataSource

    private val ioDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var apiService: UserApiService

    @Mock
    private lateinit var userList: List<UserDto>

    @Mock
    private lateinit var userResponse: UserResponse

    @Mock
    private lateinit var userDto: UserDto

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        dataSource = UserNetworkDataSource(ioDispatcher = ioDispatcher, apiService = apiService)
    }

    @Test
    fun `WHEN dataSource getUserId function is called THEN return userId`() = runBlockingTest {
        //GIVEN
        val userId = "userId"
        `when`(apiService.getUser()).thenReturn(userResponse)
        `when`(userResponse.data).thenReturn(userList)
        `when`(userList[0]).thenReturn(userDto)
        `when`(userDto.id).thenReturn(userId)

        //WHEN
        val result = dataSource.getUserId()

        //THEN
        assertEquals(userId, result)
        verify(apiService).getUser()
        verify(userResponse).data
        verify(userList)[0]
        verify(userDto).id
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(apiService, userResponse, userList, userDto)
    }

}