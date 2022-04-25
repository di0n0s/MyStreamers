package com.dionos.user.data.repository

import com.dionos.user.data.source.UserDataSource
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor(
    @Named("UserSharedPreferencesDataSource") private val userSharedPreferencesDataSource: UserDataSource,
    @Named("UserNetworkDataSource") private val userNetworkDataSource: UserDataSource
) {

    suspend fun getUserId(): String {
        var userId = userSharedPreferencesDataSource.getUserId()
        if (userId == null) {
            userId = userNetworkDataSource.getUserId()
            userSharedPreferencesDataSource.setUserId(userId!!)
        }
        return userId
    }
}