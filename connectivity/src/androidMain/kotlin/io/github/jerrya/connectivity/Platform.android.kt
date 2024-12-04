package io.github.jerrya.connectivity


actual fun getConnectivity(): ConnectivityRepository {
    val contextProvider = ContextProvider.getInstance()
    return AndroidConnectivityRepository(contextProvider.context)
}