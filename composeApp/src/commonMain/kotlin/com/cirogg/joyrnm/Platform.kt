package com.cirogg.joyrnm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform