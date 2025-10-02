package com.cirogg.joyrnm

import android.app.Application
import com.cirogg.joyrnm.di.initKoin
import org.koin.android.ext.koin.androidContext

class RnmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@RnmApp)
        }
    }
}