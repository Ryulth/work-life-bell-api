package com.ryulth.worklifebell.api.auth.application

data class TokenInfo(
    val accessToken: String,
    val refreshToken: String,
    val type: String
) {
    companion object {
        fun of(accessToken: String, refreshToken: String, type: String) = TokenInfo (
            accessToken = accessToken,
            refreshToken = refreshToken,
            type = type
        )
    }
}