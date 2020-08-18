package com.ryulth.worklifebell.api.security

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
    val type: String
)