package com.dionos.user.presentation.viewModel

import com.dionos.core.data.source.AccessTokenPreferencesDataSource
import com.dionos.user.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SignInViewModelTest {

    private lateinit var viewModel: SignInViewModel

    @Mock
    private lateinit var sharedPreferencesDataSource: AccessTokenPreferencesDataSource

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        viewModel = SignInViewModel(sharedPreferencesDataSource)
    }

    @Test
    fun `Save token intent test`() = runBlockingTest {
        val token = "token"

        `when`(sharedPreferencesDataSource.setAccessToken(token)).thenReturn(true)

        assertEquals(viewModel.isTokenSaved.value, IsTokenSavedState.Idle)

        viewModel.userIntent.send(UserIntent.SaveToken(token))

        assertEquals(viewModel.isTokenSaved.value, IsTokenSavedState.Success)

        verify(sharedPreferencesDataSource).setAccessToken(token)
    }
}