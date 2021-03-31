package com.joesamyn.envelope.repositories

import android.util.Log
import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.UserLogin
import com.joesamyn.envelope.repositories.interfaces.AuthenticationRepository
import com.joesamyn.envelope.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
    override suspend fun authenticate(user: UserLogin): Flow<DataState<AuthResp>> = flow {
        emit(DataState.Loading)

        try{
            val authResponse = authService.authenticate(user)

            // If successful response parse to user model and emit to UI
            if(authResponse.isSuccessful) {
                val authenticatedUser = authResponse.body()!!
                emit(DataState.Success(authenticatedUser))
            }
            // Authentication Request Failed
            else {
                emit(DataState.Failed)
            }

        } catch(ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}