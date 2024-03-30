package com.thusee.footballevent.di

import com.thusee.footballevent.ui.utils.WebUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWebUtil(): WebUtil {
        return WebUtil()
    }
}