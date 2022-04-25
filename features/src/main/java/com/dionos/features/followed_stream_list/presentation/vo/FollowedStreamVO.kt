package com.dionos.features.followed_stream_list.presentation.vo

import android.content.Context
import android.content.res.Resources
import com.dionos.features.R
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto

class FollowedStreamVO private constructor(
    val id: String,
    val imagePath: String,
    val title: String,
    val category: String,
    val userName: String,
    val viewers: String
) {

    companion object {
        fun fromFollowedStreamDto(
            context: Context,
            followedStreamDto: FollowedStreamDto
        ): FollowedStreamVO {
            val widthPixels = Resources.getSystem().displayMetrics.widthPixels
            val heightPixels = (widthPixels / 16) * 10

            return FollowedStreamVO(
                id = followedStreamDto.id,
                imagePath = """${followedStreamDto.thumbnailUrl.substringBeforeLast("-")}-${widthPixels}x$heightPixels.jpg""",
                userName = followedStreamDto.userName,
                category = followedStreamDto.gameName,
                title = followedStreamDto.title,
                viewers = context.resources.getQuantityString(
                    R.plurals.followed_stream_list_viewers,
                    followedStreamDto.viewerCount,
                    followedStreamDto.viewerCount
                )
            )
        }
    }


}
