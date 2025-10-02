package com.cirogg.joyrnm.di

import com.cirogg.joyrnm.data.local.DatabaseDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

val iosDatabaseModule = module {
    single { DatabaseDriverFactory().createDriver() }
}

fun initKoinIos() {
    startKoin {
        modules(appModules + iosDatabaseModule)
    }
}