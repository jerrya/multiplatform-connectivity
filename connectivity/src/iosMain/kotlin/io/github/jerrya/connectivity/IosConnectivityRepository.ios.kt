package io.github.jerrya.connectivity

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_status_satisfied
import platform.darwin.dispatch_queue_create

class IosConnectivityRepository : ConnectivityRepository {

    override fun latestStatus(): Flow<ConnectivityRepository.Status> {
        val monitor = nw_path_monitor_create()
        val dispatchQueue = dispatch_queue_create(DISPATCH_QUEUE_LABEL, null)

        return callbackFlow {
            nw_path_monitor_set_update_handler(monitor) { path ->
                val status = nw_path_get_status(path)
                if (status == nw_path_status_satisfied) {
                    trySend(ConnectivityRepository.Status.Online)
                } else {
                    trySend(ConnectivityRepository.Status.Offline)
                }
            }

            nw_path_monitor_set_queue(monitor, dispatchQueue)
            nw_path_monitor_start(monitor)

            awaitClose {
                nw_path_monitor_cancel(monitor)
            }
        }
    }

    companion object {
        private const val DISPATCH_QUEUE_LABEL = "io.github.jerrya.connectivity"
    }
}