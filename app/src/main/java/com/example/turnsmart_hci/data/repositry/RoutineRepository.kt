package com.example.turnsmart_hci.data.repositry

import com.example.turnsmart_hci.data.model.Routine
import com.example.turnsmart_hci.data.remote.RoutineRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    val routines: Flow<List<Routine>> =
        remoteDataSource.routines
            .map { it.map { jt -> jt.asModel() } }

    val currentRoutine = routines.map { it.firstOrNull { jt -> jt is Routine} }

    suspend fun executeRoutine(routineId: String): Array<*> {
        return remoteDataSource.executeRoutine(routineId)
    }
}