package com.thusee.footballevent.ui.common.errors.errorHandler

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ErrorMessageUtilModule {

    @Binds
    abstract fun bindErrorMessageUtil(
        errorMessageResourceUtilImpl: ErrorMessageResourceUtilImpl
    ): ErrorMessageResourceUtil
}