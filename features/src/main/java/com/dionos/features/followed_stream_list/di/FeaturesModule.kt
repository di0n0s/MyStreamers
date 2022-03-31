package com.dionos.features.followed_stream_list.di

import com.dionos.features.followed_stream_list.data.service.TwitchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
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