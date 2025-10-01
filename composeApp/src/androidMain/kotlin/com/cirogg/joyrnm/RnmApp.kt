package com.cirogg.joyrnm

import android.app.Application
import com.cirogg.joyrnm.di.androidDatabaseModule
import com.cirogg.joyrnm.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class RnmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RnmApp)
            modules(appModules + androidDatabaseModule)
        }
    }
}