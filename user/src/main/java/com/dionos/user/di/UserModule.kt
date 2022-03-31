package com.dionos.user.di

import com.dionos.user.data.service.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object UserModule {

    @Provides
    fun provideUserApiService(
        retrofitBuilder: Retrofit.Builder,
    ): UserApiService {

        return retrofitBuilder
            .build()
            .create(UserApiService::class.java)
    }
}