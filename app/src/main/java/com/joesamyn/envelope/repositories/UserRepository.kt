package com.joesamyn.envelope.repositories

import android.util.Log
import com.joesamyn.envelope.models.RefreshTokenReq
import com.joesamyn.envelope.models.User
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit


class UserRepository constructor(private val authService: AuthenticationService) {

    lateinit var user: User
    lateinit var accessTokenFetchTs: Date
    val validityTime = 10

    private val TAG = UserRepository::class.java.simpleName

    /**
     * Get Access token for user
     */
    suspend fun getAccessToken(): String{
        return user.AccessToken
    }

    /**
     * Get refresh token for user
     */
    suspend fun getRefreshToken() {

        // Check if we need to refresh the token
        val accessTokenLifetime: Long = Date().time - accessTokenFetchTs.time
        val duration = TimeUnit.MINUTES.convert(accessTokenLifetime, TimeUnit.MILLISECONDS)

        // If we are under the expiration time just return. No need to get new token if access token is still valid
        if(duration < 10)
            return

        // Else fetch new access token using the refresh token
        try{
            val refreshResp = authService.refreshToken(RefreshTokenReq(user.RefreshToken))

            // If Successful response set new token values
            if(refreshResp.isSuccessful) {
                val tokens = refreshResp.body()!!
                user.AccessToken = tokens.AccessToken
                user.RefreshToken = tokens.RefreshToken
            }
        } catch(ex: Exception){
            Log.e(TAG, ex.message!!)
        }

    }

    /**
     * Set the user access token data when logged in
     */
    fun setUserTokens(user: User) {
        this.user = user

        // Set the fetch timestamp so we know when to refresh
        accessTokenFetchTs = Date()
    }
}