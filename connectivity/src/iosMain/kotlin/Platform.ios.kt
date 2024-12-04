package io.github.jerrya.connectivity

actual fun getConnectivity(): ConnectivityRepository {
    return IosConnectivityRepository()
}