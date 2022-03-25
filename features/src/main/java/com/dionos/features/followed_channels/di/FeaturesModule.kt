package com.dionos.features.followed_channels.di

import com.dionos.features.followed_channels.data.service.TwitchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object FeaturesModule {

    @Provides
    fun provideTwitchApiService(
        retrofitBuilder: Retrofit.Builder,
    ): TwitchApiService {

        return retrofitBuilder
            .build()
            .create(TwitchApiService::class.java)
    }
}