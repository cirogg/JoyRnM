package com.cirogg.joyrnm

import androidx.compose.ui.window.ComposeUIViewController
import com.cirogg.joyrnm.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) {
    App()
}