package com.dionos.features.followed_stream_list.di

import com.dionos.features.followed_stream_list.data.service.TwitchApiService
import com.dionos.features.followed_stream_list.data.source.FeaturesDataSource
import com.dionos.features.followed_stream_list.data.source.FeaturesNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeaturesModule {

    companion object {
        @Provides
        fun provideTwitchApiService(
            retrofitBuilder: Retrofit.Builder,
        ): TwitchApiService {

            return retrofitBuilder
                .build()
                .create(TwitchApiService::class.java)
        }
    }

    @Binds
    @Named("FeaturesNetworkDataSource")
    abstract fun bindFeaturesNetworkDataSource(featuresNetworkDataSource: FeaturesNetworkDataSource): FeaturesDataSource
}