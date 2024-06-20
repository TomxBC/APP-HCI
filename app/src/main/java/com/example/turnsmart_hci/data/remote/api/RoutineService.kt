package com.example.turnsmart_hci.data.remote.api

import com.example.turnsmart_hci.data.remote.model.RemoteResult
import com.example.turnsmart_hci.data.remote.model.RemoteRoutine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RoutineService {
    @PUT("routines/{routineId}/execute")
    suspend fun executeRoutine(
        @Path("routineId") routineId: String
    ): Response<RemoteResult<Array<*>>>

    @GET("routines")
    suspend fun getAllRoutines(): Response<RemoteResult<List<RemoteRoutine>>>
}