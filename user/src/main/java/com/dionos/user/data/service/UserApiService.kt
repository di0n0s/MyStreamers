package com.dionos.user.data.service

import com.dionos.user.data.response.UserResponse
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUser(): UserResponse

}