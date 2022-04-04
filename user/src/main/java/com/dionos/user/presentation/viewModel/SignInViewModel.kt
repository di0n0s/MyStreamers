package com.dionos.user.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dionos.core.data.source.AccessTokenPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val sharedPreferencesDataSource: AccessTokenPreferencesDataSource) :
    ViewModel() {

    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)

    private val _isTokenSaved = MutableStateFlow<IsTokenSavedState>(IsTokenSavedState.Idle)
    val isTokenSaved: StateFlow<IsTokenSavedState>
        get() = _isTokenSaved

    init {
        handleUserIntent()
    }

    private fun handleUserIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is UserIntent.SaveToken -> saveToken(it.token)
                }
            }
        }
    }

    private fun saveToken(token: String) {
        if (sharedPreferencesDataSource.setAccessToken(token)) {
            _isTokenSaved.value = IsTokenSavedState.Success
        }
    }

}

sealed class UserIntent {
    data class SaveToken(val token: String) : UserIntent()
}

sealed class IsTokenSavedState {
    object Idle : IsTokenSavedState()
    object Success : IsTokenSavedState()
}