package com.dionos.features.followed_stream_list.presentation.vo

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class FollowedStreamVOTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val followedStreamDto = FollowedStreamDto(
        id = "45274130444",
        gameName = "League of Legends",
        thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_iamcristinini-{width}x{height}.jpg",
        title = "\uD83D\uDD34 TU DUO FAVORITO | !prime !sub | Follow @IamCristinini",
        userName = "IamCristinini",
        viewerCount = 5789
    )


    @Test
    fun `GIVEN a Context and a FollowedStreamDto WHEN fromFollowedStreamDto function is called THEN return a FollowedStreamVO`() {
        //GIVEN


        //WHEN
        val result = FollowedStreamVO.fromFollowedStreamDto(context, followedStreamDto)

        //THEN
        assertEquals(result.id, followedStreamDto.id)
        assertEquals(result.category, followedStreamDto.gameName)
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val heightPixels = (widthPixels / 16) * 10
        assertEquals(
            result.imagePath,
            """https://static-cdn.jtvnw.net/previews-ttv/live_user_iamcristinini-${widthPixels}x$heightPixels.jpg"""
        )
        assertEquals(result.title, followedStreamDto.title)
        assertEquals(result.userName, followedStreamDto.userName)
        assertEquals(result.viewers, "5789 viewers")
    }
}