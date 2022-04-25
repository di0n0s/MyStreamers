package com.dionos.mystreamers.presentation.view.fake

import com.dionos.features.followed_stream_list.data.response.FollowedStreamListResponse
import com.dionos.features.followed_stream_list.data.response.dto.FollowedStreamDto
import com.dionos.features.followed_stream_list.data.response.dto.PaginationDto
import com.dionos.features.followed_stream_list.data.source.FeaturesDataSource
import javax.inject.Inject
import kotlin.random.Random

class FakeFeaturesNetworkDataSource @Inject constructor() : FeaturesDataSource {

    override suspend fun getFollowedStreamList(
        userId: String,
        cursor: String?,
        loadSize: Int
    ): FollowedStreamListResponse {
        val list = listOf(
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "PORCINOS FC | ROAD TO 8 DIVISION | LA COSA SE PONE MUY SERIA",
                userName = "ibai",
                gameName = "FIFA 22",
                viewerCount = 65443,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_ibai-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "우왁굳 애니 티어 게임",
                userName = "우왁굳",
                gameName = "Just Chatting",
                viewerCount = 15118,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_woowakgood-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "AEG Un33d - #1 EU - WORLDS CE VENDREDI - !tournoi d'entrainement à 11h",
                userName = "Un33D",
                gameName = "Teamfight Tactics",
                viewerCount = 2845,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_un33d-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "WAKZ SOLOQ FULL TRYHARD GLUTONNY ET CARL M'ONT INVESTI DE LEUR ESPRIT DE CHAMPIONS DU MONDE JE NE PEUX PLUS PERDRE (OU PRESQUE)",
                userName = "Solary",
                gameName = "Teamfight Tactics",
                viewerCount = 2845,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_un33d-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "AEG Un33d - #1 EU - WORLDS CE VENDREDI - !tournoi d'entrainement à 11h",
                userName = "Un33D",
                gameName = "Teamfight Tactics",
                viewerCount = 2845,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_un33d-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "WAKZ SOLOQ FULL TRYHARD GLUTONNY ET CARL M'ONT INVESTI DE LEUR ESPRIT DE CHAMPIONS DU MONDE JE NE PEUX PLUS PERDRE (OU PRESQUE)",
                userName = "Solary",
                gameName = "Teamfight Tactics",
                viewerCount = 2845,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_un33d-{width}x{height}.jpg"
            ),
            FollowedStreamDto(
                id = Random.nextLong().toString(),
                title = "우왁굳 애니 티어 게임",
                userName = "우왁굳",
                gameName = "Just Chatting",
                viewerCount = 15118,
                thumbnailUrl = "https://static-cdn.jtvnw.net/previews-ttv/live_user_woowakgood-{width}x{height}.jpg"
            )
        )
        return FollowedStreamListResponse(
            data = list,
            pagination = PaginationDto(cursor = Random.nextLong().toString())
        )
    }
}