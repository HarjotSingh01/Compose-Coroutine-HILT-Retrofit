package com.example.coroutinesretrofit.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Harjot Singh on 18/04/23
 */
@Module
@InstallIn(ViewModelComponent::class)
internal class DispatcherModule {
    @IODispatcher
    @Provides
    @ViewModelScoped
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @MainDispatcher
    @Provides
    @ViewModelScoped
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}