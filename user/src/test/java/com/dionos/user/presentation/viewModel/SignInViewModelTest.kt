package com.dionos.user.presentation.viewModel

import com.dionos.core.data.source.AccessTokenPreferencesDataSource
import com.dionos.test_utils.MainCoroutineRule
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
    fun `GIVEN SaveTokenUserIntent WHEN viewModel userIntent is Sent THEN setAccessToken datasource function is called`() =
        runBlockingTest {
            //GIVEN
            val token = "token"
            `when`(sharedPreferencesDataSource.setAccessToken(token)).thenReturn(true)
            val beforeWhenIsTokenSavedValue = viewModel.isTokenSaved.value

            //WHEN
            viewModel.userIntent.send(UserIntent.SaveToken(token))

            //THEN
            assertEquals(beforeWhenIsTokenSavedValue, IsTokenSavedState.Idle)
            assertEquals(viewModel.isTokenSaved.value, IsTokenSavedState.Success)
            verify(sharedPreferencesDataSource).setAccessToken(token)
        }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesDataSource)
    }
}