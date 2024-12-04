package io.github.jerrya.connectivity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface ConnectivityRepository {
    fun latestStatus(): Flow<Status>

    suspend fun isOnline() = latestStatus().first() == Status.Online

    sealed interface Status {
        data object Online : Status
        data object Offline : Status
    }
}
