package com.dionos.user.data.source

interface UserDataSource {
    suspend fun getUserId(): String?
    suspend fun setUserId(userId: String) {}
}