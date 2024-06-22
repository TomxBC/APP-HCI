package com.example.turnsmart_hci.data.remote

import com.example.turnsmart_hci.data.remote.api.RoutineService
import com.example.turnsmart_hci.data.remote.model.RemoteRoutine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoutineRemoteDataSource (
    private val routineService: RoutineService
) : RemoteDataSource() {

    val routines: Flow<List<RemoteRoutine>> = flow {
        while(true) {
            val routines = handleApiResponse {
                routineService.getAllRoutines()
            }
            emit(routines)
            delay(DELAY)
        }
    }

    suspend fun executeRoutine(routineId: String): Array<*> {
        return handleApiResponse {
            routineService.executeRoutine(routineId)
        }
    }

    companion object {
        const val DELAY: Long = 10000
    }
}