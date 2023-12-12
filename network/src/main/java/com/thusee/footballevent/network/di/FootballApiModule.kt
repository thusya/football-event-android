package com.thusee.footballevent.network.di

import com.thusee.footballevent.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FootballApiModule {

    @Singleton
    @Provides
    fun provideFootBallApi(
        retrofit: Retrofit,
    ): ApiService = retrofit.create(ApiService::class.java)
}