package com.thusee.footballevent.di

import com.thusee.footballevent.data.repository.MatchDataRepositoryImpl
import com.thusee.footballevent.domain.repository.MatchDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MatchDataRepoModule {

    @Binds
    abstract fun bindMatchDataRepo(
        matchDataRepositoryImpl: MatchDataRepositoryImpl
    ): MatchDataRepository
}