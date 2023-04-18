package com.example.coroutinesretrofit.data.di

import javax.inject.Qualifier

/**
 * Created by Harjot Singh on 18/04/23
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher