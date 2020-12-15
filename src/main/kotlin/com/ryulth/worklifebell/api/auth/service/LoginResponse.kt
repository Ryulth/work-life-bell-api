package com.ryulth.worklifebell.api.auth.service

data class LoginResponse(
    val userId: Long,
    val publicId: String,
    val tokenInfo: TokenInfo
) {
    companion object {
        fun of(userId: Long, publicId: String, tokenInfo: TokenInfo) = LoginResponse(
            userId = userId,
            publicId = publicId,
            tokenInfo = tokenInfo
        )
    }
}
