package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.UserLogin
import com.joesamyn.envelope.repositories.interfaces.AuthenticationRepository

/**
 * Handles all authentication requests for user
 */
class AuthenticationRepositoryImpl
constructor(
    private val authService: AuthenticationService
    ): AuthenticationRepository {

    /**
     * Authenticate user with the credentials provided
     */
    override suspend fun authenticate(user: UserLogin): AuthResp {
        return authService.authenticate(user)
    }
}