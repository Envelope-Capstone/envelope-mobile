package com.joesamyn.envelope.repositories.interfaces

import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.UserLogin

interface AuthenticationRepository {

    /**
     * Makes an authentication request to the JWT API
     */
    suspend fun authenticate(user: UserLogin): AuthResp
}