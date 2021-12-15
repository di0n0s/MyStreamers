package com.dionos.user.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPreferencesDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    private val preferences =
        context.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)

    companion object {
        private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
    }

    fun setAccessToken(token: String): Boolean {
        with(preferences.edit()) {
            putString(ACCESS_TOKEN_KEY, token)
            return commit()
        }
    }
}