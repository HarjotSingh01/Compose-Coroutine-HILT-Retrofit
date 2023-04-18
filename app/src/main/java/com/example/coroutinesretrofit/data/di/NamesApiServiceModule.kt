package com.example.coroutinesretrofit.data.di

import com.example.coroutinesretrofit.data.remote.NamesApiService
import com.example.coroutinesretrofit.utils.BaseApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Harjot Singh on 18/04/23
 */
@Module
@InstallIn(ViewModelComponent::class)
internal class NamesApiServiceModule {
    @Provides
    @ViewModelScoped
    fun provideNamesApiService(): NamesApiService {
        return BaseApiUtils.getRetrofitBuilder("https://www.google.co.in/")
            .create(NamesApiService::class.java)
    }
}