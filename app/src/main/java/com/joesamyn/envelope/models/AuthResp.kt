package com.joesamyn.envelope.models

import com.google.gson.annotations.SerializedName

/**
 * Model that holds the tokens received from a successful authentication request
 */
data class AuthResp(
    @SerializedName("access_token")
    var AccessToken: String,
    @SerializedName("refresh_token")
    var RefreshToken: String
)
