package com.joesamyn.envelope.repositories.interfaces

import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.UserLogin
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    /**
     * Makes an authentication request to the JWT API
     */
    suspend fun authenticate(user: UserLogin): Flow<DataState<AuthResp>>
}