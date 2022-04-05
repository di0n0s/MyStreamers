package com.dionos.user.data.source

import android.content.Context
import android.content.SharedPreferences
import com.dionos.user.MainCoroutineRule
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
class UserSharedPreferencesDataSourceTest {

    private lateinit var sharedPreferencesDataSource: UserSharedPreferencesDataSource

    private val mainDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        `when`(
            context.getSharedPreferences(
                UserSharedPreferencesDataSource::class.java.name,
                Context.MODE_PRIVATE
            )
        ).thenReturn(sharedPreferences)

        sharedPreferencesDataSource =
            UserSharedPreferencesDataSource(context = context, mainDispatcher = mainDispatcher)

        verify(context).getSharedPreferences(
            UserSharedPreferencesDataSource::class.java.name,
            Context.MODE_PRIVATE
        )
    }

    @Test
    fun `when dataSource setUserId function is called then save userId in sharedPreferences`() =
        runBlockingTest {
            val userId = "userId"

            `when`(sharedPreferences.edit()).thenReturn(editor)

            sharedPreferencesDataSource.setUserId(userId)

            verify(sharedPreferences).edit()
            verify(editor).putString("USER_ID_KEY", userId)
            verify(editor).apply()
        }

    @Test
    fun `when dataSource getUser function is called then return userId from sharedPreferences`() =
        runBlockingTest {
            val userId = "userId"

            `when`(sharedPreferences.getString("USER_ID_KEY", null)).thenReturn(userId)

            assertEquals(userId, sharedPreferencesDataSource.getUserId())

            verify(sharedPreferences).getString("USER_ID_KEY", null)
        }

    @After
    fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(context, sharedPreferences, editor)
    }
}