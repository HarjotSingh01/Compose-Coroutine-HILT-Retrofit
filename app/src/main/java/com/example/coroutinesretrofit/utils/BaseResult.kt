package com.example.coroutinesretrofit.utils

/**
 * Created by Harjot Singh on 18/04/23
 */
sealed interface BaseResult<out T : Any> {
    object Loading : BaseResult<Nothing>

    data class Success<out T : Any>(val data: T) : BaseResult<T>

    data class Error(val exception: Throwable? = null): BaseResult<Nothing>
}