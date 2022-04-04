package com.dionos.core.di

import com.dionos.core.BuildConfig
import com.dionos.core.data.source.AccessTokenPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object CoreModule {

    @Provides
    fun provideRetrofitBuilder(
        sharedPreferencesDataSource: AccessTokenPreferencesDataSource,
    ): Retrofit.Builder {
        val builder = OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
        }

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder
                .addNetworkInterceptor(httpLoggingInterceptor)
            //TODO add chuck
            //.addNetworkInterceptor(chuckInterceptor)
        }

        builder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
            requestBuilder.header("Client-Id", "f368puphbblwvu2sx5l1ufqfo5izet")
            sharedPreferencesDataSource.getAccessToken()?.let {
                requestBuilder.header("Authorization", it)
            }

            chain.proceed(requestBuilder.build())
        }

        return Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
    }

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

