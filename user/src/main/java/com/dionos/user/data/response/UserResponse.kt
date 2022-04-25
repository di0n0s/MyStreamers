package com.dionos.user.data.response

import com.dionos.user.data.response.dto.UserDto
import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("data") val data: List<UserDto>)