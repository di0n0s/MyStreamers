package com.dionos.network.di

import com.dionos.core.BuildConfig
import com.dionos.user.data.UserSharedPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(FragmentComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitBuilder(
        sharedPreferencesDataSource: UserSharedPreferencesDataSource,
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
            .baseUrl("https://api.twitch.tv/helix")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
    }
}