package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.RefreshTokenReq
import com.joesamyn.envelope.models.RefreshTokenResp
import com.joesamyn.envelope.models.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationService {

    @POST("api/login")
    @Headers("Content-Type: application/json")
    suspend fun authenticate(@Body user: UserLogin): Response<AuthResp>

    @POST("token/refresh")
    @Headers("Content-Type: application/json")
    suspend fun refreshToken(@Body refreshToken: RefreshTokenReq): Response<RefreshTokenResp>
}