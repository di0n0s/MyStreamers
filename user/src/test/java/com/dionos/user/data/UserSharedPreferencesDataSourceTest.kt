package com.dionos.user.data

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserSharedPreferencesDataSourceTest {

    private lateinit var sharedPreferencesDataSource: UserSharedPreferencesDataSource

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setUp() {
        `when`(
            context.getSharedPreferences(
                "com.dionos.user.data.UserSharedPreferencesDataSource",
                Context.MODE_PRIVATE
            )
        ).thenReturn(sharedPreferences)

        sharedPreferencesDataSource = UserSharedPreferencesDataSource(context)
    }

    @Test
    fun setAccessToken() {
        val token = "token"

        `when`(sharedPreferences.edit()).thenReturn(editor)

        `when`(editor.commit()).thenReturn(true)

        assertTrue(sharedPreferencesDataSource.setAccessToken(token))

        verify(editor).putString("ACCESS_TOKEN_KEY", token)

        verify(editor).commit()
    }
}