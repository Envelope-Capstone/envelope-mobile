package com.joesamyn.envelope.repositories

import com.joesamyn.envelope.models.User

class UserRepository constructor() {

    var user: User? = null
    val validityTime = 10

    /**
     * Get Access token for user
     */
    fun getAccessToken(): String{
        return user!!.AccessToken
    }

    /**
     * Get refresh token for user
     */
    fun getRefreshToken(): String {
        return user!!.RefreshToken
    }
}