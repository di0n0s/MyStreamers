package com.dionos.core.data

import android.content.Context
import android.content.SharedPreferences
import com.dionos.core.data.source.AccessTokenPreferencesDataSource
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AccessTokenPreferencesDataSourceTest {

    private lateinit var sharedPreferencesDataSource: AccessTokenPreferencesDataSource

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
                AccessTokenPreferencesDataSource::class.java.name,
                Context.MODE_PRIVATE
            )
        ).thenReturn(sharedPreferences)

        sharedPreferencesDataSource = AccessTokenPreferencesDataSource(context)
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