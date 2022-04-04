package com.dionos.user.data.source

import com.dionos.user.data.service.UserApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserNetworkDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiService: UserApiService
) : UserDataSource {

    override suspend fun getUserId(): String =
        withContext(ioDispatcher) { apiService.getUser().data[0].id }
}