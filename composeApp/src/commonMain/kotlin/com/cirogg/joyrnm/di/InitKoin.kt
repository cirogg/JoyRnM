package com.cirogg.joyrnm.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

private var started = false

fun initKoin(extra: KoinAppDeclaration? = null) {
    if (!started) {
        started = true
        startKoin {
            extra?.invoke(this)
            modules(
                appModules + platformModule
            )
        }
    }
}