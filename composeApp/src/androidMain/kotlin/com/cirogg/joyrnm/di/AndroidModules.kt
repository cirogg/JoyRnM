package com.cirogg.joyrnm.di

import com.cirogg.joyrnm.data.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single { DatabaseDriverFactory(androidContext()).createDriver() }
}