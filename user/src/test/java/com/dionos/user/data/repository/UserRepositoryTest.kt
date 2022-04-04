package com.dionos.user.data.repository

import com.dionos.user.data.source.UserDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.inOrder
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
    fun `when sharedPreferences getUserId expect return userId`() =
        runBlockingTest {
            val userId = "userId"
            `when`(userSharedPreferencesDataSource.getUserId()).thenReturn(userId)

            assertEquals(userId, repository.getUserId())

            val inOrder = inOrder(userSharedPreferencesDataSource, userNetworkDataSource)

            inOrder.verify(userSharedPreferencesDataSource).getUserId()
            inOrder.verifyNoMoreInteractions()
        }

    @Test
    fun `when sharedPreferences getUserId return null expect network return it and save it in sharedPreferences`() =
        runBlockingTest {
            val userId = "userId"
            `when`(userSharedPreferencesDataSource.getUserId()).thenReturn(null)
            `when`(userNetworkDataSource.getUserId()).thenReturn(userId)

            assertEquals(userId, repository.getUserId())

            val inOrder = inOrder(userSharedPreferencesDataSource, userNetworkDataSource)

            inOrder.verify(userSharedPreferencesDataSource).getUserId()
            inOrder.verify(userNetworkDataSource).getUserId()
            inOrder.verify(userSharedPreferencesDataSource).setUserId(userId)
            inOrder.verifyNoMoreInteractions()
        }
}