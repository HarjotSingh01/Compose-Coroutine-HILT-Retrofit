package com.example.coroutinesretrofit.domain.repo

import com.example.coroutinesretrofit.data.di.IODispatcher
import com.example.coroutinesretrofit.data.remote.NamesApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Harjot Singh on 18/04/23
 */
internal interface UserNameRepo {
    suspend fun fetchUserNames(): Response<List<String>>
}

internal class UserNameRepoImpl @Inject constructor(
    private val apiService: NamesApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) :
    UserNameRepo {
    override suspend fun fetchUserNames(): Response<List<String>> =
        withContext(dispatcher) {
            apiService.getNames()
        }
}