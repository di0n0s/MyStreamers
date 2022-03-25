package com.dionos.user.data

import com.dionos.user.data.service.UserApiService
import javax.inject.Inject

class UserNetworkDataSource @Inject constructor(private val apiService: UserApiService) {

    suspend fun getUserId(): String = apiService.getUser().data[0].id
}