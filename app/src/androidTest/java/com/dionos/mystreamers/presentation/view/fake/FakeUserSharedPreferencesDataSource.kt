package com.dionos.mystreamers.presentation.view.fake

import com.dionos.user.data.source.UserDataSource
import javax.inject.Inject

class FakeUserSharedPreferencesDataSource @Inject constructor() : UserDataSource {

    override suspend fun getUserId(): String = "userId"
}