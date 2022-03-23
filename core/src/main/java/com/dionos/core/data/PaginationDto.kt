package com.dionos.core.data

import com.google.gson.annotations.SerializedName

data class PaginationDto(@SerializedName("cursor") val cursor: String)