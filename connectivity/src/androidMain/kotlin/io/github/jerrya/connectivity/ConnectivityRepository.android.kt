package io.github.jerrya.connectivity

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class AndroidConnectivityRepository(
    context: Context
) : ConnectivityRepository {

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private val connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun latestStatus(): Flow<ConnectivityRepository.Status> = callbackFlow {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(ConnectivityRepository.Status.Online)
            }

            override fun onUnavailable() {
                trySend(ConnectivityRepository.Status.Offline)
            }

            override fun onLost(network: Network) {
                trySend(ConnectivityRepository.Status.Offline)
            }
        }

        networkCallback?.let { connectivityManager.registerDefaultNetworkCallback(it) }

        awaitClose {
            networkCallback?.let { connectivityManager.unregisterNetworkCallback(it) }
        }
    }
}