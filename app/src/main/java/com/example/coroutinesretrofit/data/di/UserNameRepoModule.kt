package com.example.coroutinesretrofit.data.di

import com.example.coroutinesretrofit.data.remote.NamesApiService
import com.example.coroutinesretrofit.domain.repo.UserNameRepo
import com.example.coroutinesretrofit.domain.repo.UserNameRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Harjot Singh on 18/04/23
 */
@Module
@InstallIn(ViewModelComponent::class)
internal class UserNameRepoModule {
    @Provides
    @ViewModelScoped
    fun provideUserNameRepo(
        apiService: NamesApiService,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): UserNameRepo = UserNameRepoImpl(apiService, dispatcher)
}