package io.github.jerrya.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityRepository {
    fun latestStatus(): Flow<Status>

    sealed interface Status {
        data object Online: Status
        data object Offline: Status
    }
}
