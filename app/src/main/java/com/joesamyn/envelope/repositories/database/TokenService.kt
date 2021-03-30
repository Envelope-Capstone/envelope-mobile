package com.joesamyn.envelope.repositories.database

import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    
    @POST("token/refresh")
    suspend fun refresh(@Body token: String)
}