package com.thusee.footballevent.network.di

import com.thusee.footballevent.network.repository.FootballDataRemote
import com.thusee.footballevent.network.repository.impl.FootballDataRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FootballDataRemoteModule {

    @Binds
    abstract fun bindFootballDataRemoteModule(
        footballDataRemoteImpl: FootballDataRemoteImpl
    ): FootballDataRemote
}