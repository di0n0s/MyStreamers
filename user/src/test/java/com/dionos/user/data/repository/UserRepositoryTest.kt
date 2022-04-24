package com.dionos.user.data.repository

import com.dionos.user.data.source.UserDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    private lateinit var repository: UserRepository

    @Mock
    private lateinit var userSharedPreferencesDataSource: UserDataSource

    @Mock
    private lateinit var userNetworkDataSource: UserDataSource

    @Before
    fun setUp() {
        repository = UserRepository(
            userSharedPreferencesDataSource = userSharedPreferencesDataSource,
            userNetworkDataSource = userNetworkDataSource
        )
    }

    @Test
    fun `GIVEN sharedPreferences return userId WHEN getUserId is called THEN sharedPreferences return userId`() =
        runBlockingTest {
            //GIVEN
            val userId = "userId"
            `when`(userSharedPreferencesDataSource.getUserId()).thenReturn(userId)

            //WHEN
            val result = repository.getUserId()

            //THEN
            assertEquals(userId, result)

            verify(userSharedPreferencesDataSource).getUserId()
        }

    @Test
    fun `GIVEN sharedPreferences no return userId WHEN getUserId is called THEN api return userId`() =
        runBlockingTest {
            //GIVEN
            val userId = "userId"
            `when`(userSharedPreferencesDataSource.getUserId()).thenReturn(null)
            `when`(userNetworkDataSource.getUserId()).thenReturn(userId)

            //WHEN
            val result = repository.getUserId()

            //THEN
            assertEquals(userId, result)

            val inOrder = inOrder(userSharedPreferencesDataSource, userNetworkDataSource)
            inOrder.verify(userSharedPreferencesDataSource).getUserId()
            inOrder.verify(userNetworkDataSource).getUserId()
            inOrder.verify(userSharedPreferencesDataSource).setUserId(userId)
        }

    @Test
    fun tearDown() {
        verifyNoMoreInteractions(userSharedPreferencesDataSource, userNetworkDataSource)
    }
}