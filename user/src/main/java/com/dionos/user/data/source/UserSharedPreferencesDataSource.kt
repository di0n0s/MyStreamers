package com.dionos.user.data.source

import android.content.Context
import com.dionos.core.di.MainDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSharedPreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : UserDataSource {

    private val preferences =
        context.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)

    companion object {
        private const val USER_ID_KEY = "USER_ID_KEY"
    }

    override suspend fun getUserId(): String? =
        withContext(mainDispatcher) { preferences.getString(USER_ID_KEY, null) }

    override suspend fun setUserId(userId: String) {
        withContext(mainDispatcher) {
            with(preferences.edit()) {
                putString(USER_ID_KEY, userId)
                apply()
            }
        }
    }
}