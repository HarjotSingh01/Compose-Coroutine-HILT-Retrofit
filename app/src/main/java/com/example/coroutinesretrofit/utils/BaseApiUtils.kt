package com.example.coroutinesretrofit.utils

import retrofit2.Retrofit

/**
 * Created by Harjot Singh on 18/04/23
 */
object BaseApiUtils {
    fun getRetrofitBuilder(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
    }
}