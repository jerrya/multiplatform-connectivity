package io.github.jerrya.connectivity.example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
