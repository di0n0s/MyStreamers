package com.dionos.user.data.source

import android.content.Context
import android.content.SharedPreferences
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
class UserSharedPreferencesDataSourceTest {

    private lateinit var dataSource: UserSharedPreferencesDataSource

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

        dataSource =
            UserSharedPreferencesDataSource(context = context, mainDispatcher = mainDispatcher)

        verify(context).getSharedPreferences(
            UserSharedPreferencesDataSource::class.java.name,
            Context.MODE_PRIVATE
        )
    }

    @Test
    fun `GIVEN an userID WHEN dataSource call setUserId function THEN save userId in sharedPreferences`() =
        runBlockingTest {
            //GIVEN
            val userId = "userId"
            `when`(sharedPreferences.edit()).thenReturn(editor)

            //WHEN
            dataSource.setUserId(userId)

            //THEN
            verify(sharedPreferences).edit()
            verify(editor).putString("USER_ID_KEY", userId)
            verify(editor).apply()
        }

    @Test
    fun `WHEN dataSource getUser function is called THEN return userId from sharedPreferences`() =
        runBlockingTest {
            //GIVEN
            val userId = "userId"
            `when`(sharedPreferences.getString("USER_ID_KEY", null)).thenReturn(userId)

            //WHEN
            val result = dataSource.getUserId()

            //THEN
            assertEquals(userId, result)
            verify(sharedPreferences).getString("USER_ID_KEY", null)
        }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(context, sharedPreferences, editor)
    }
}