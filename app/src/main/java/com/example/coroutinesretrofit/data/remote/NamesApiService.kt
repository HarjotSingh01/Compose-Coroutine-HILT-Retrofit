package com.example.coroutinesretrofit.data.remote

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Harjot Singh on 18/04/23
 */
internal interface NamesApiService {
    @GET("names")
    suspend fun getNames(): Response<List<String>>
}