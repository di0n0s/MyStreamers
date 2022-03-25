package com.dionos.features.followed_stream_list.data.response.dto

import com.google.gson.annotations.SerializedName

data class PaginationDto(@SerializedName("cursor") val cursor: String)