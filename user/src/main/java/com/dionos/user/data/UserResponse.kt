package com.dionos.user.data

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("data") val data: List<UserDto>)