package com.dionos.user.presentation.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dionos.user.data.UserSharedPreferencesDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


class SignInViewModel @ViewModelInject constructor(private val sharedPreferencesDataSource: UserSharedPreferencesDataSource) :
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
        viewModelScope.launch {
            if (sharedPreferencesDataSource.setAccessToken(token)) {
                _isTokenSaved.value = IsTokenSavedState.Success
            }
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