package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.User
import java.time.LocalDate
import java.util.*


class UserRepository constructor() {

    lateinit var user: User
    lateinit var accessTokenFetchTs: Date
    val validityTime = 10

    /**
     * Get Access token for user
     */
    suspend fun getAccessToken(): String{
        return user.AccessToken
    }

    /**
     * Get refresh token for user
     */
    suspend fun getRefreshToken(): String {
        return user.RefreshToken
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