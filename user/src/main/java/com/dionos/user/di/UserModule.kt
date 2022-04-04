package com.dionos.user.di

import com.dionos.user.data.service.UserApiService
import com.dionos.user.data.source.UserDataSource
import com.dionos.user.data.source.UserNetworkDataSource
import com.dionos.user.data.source.UserSharedPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {

    @Provides
    fun provideUserApiService(
        retrofitBuilder: Retrofit.Builder,
    ): UserApiService {

        return retrofitBuilder
            .build()
            .create(UserApiService::class.java)
    }

    @Binds
    @Named("UserNetworkDataSource")
    abstract fun bindUserNetworkDataSource(userNetworkDataSource: UserNetworkDataSource): UserDataSource

    @Binds
    @Named("UserSharedPreferencesDataSource")
    abstract fun bindUserSharedPreferencesDataSource(userSharedPreferencesDataSource: UserSharedPreferencesDataSource): UserDataSource
}