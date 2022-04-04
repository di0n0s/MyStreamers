package com.dionos.user.data.source

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPreferencesDataSource @Inject constructor(@ApplicationContext private val context: Context) :
    UserDataSource {

    private val preferences =
        context.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID_KEY = "USER_ID_KEY"
    }

    override suspend fun getUserId(): String? = preferences.getString(USER_ID_KEY, null)

    override suspend fun setUserId(userId: String) {
        with(preferences.edit()) {
            putString(USER_ID_KEY, userId)
            apply()
        }
    }
}