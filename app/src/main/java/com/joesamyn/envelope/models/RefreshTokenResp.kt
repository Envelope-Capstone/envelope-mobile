package com.joesamyn.envelope.models

/**
 * Class to hold the new access token and refresh token after successful token refresh request
 */
data class RefreshTokenResp(
    var AccessToken: String,
    var RefreshToken: String
)
