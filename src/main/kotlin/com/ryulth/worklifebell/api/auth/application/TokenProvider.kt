package com.ryulth.worklifebell.api.auth.application

import com.ryulth.worklifebell.api.user.domain.User
import org.springframework.stereotype.Service

@Service
interface TokenProvider {
    fun generatedToken(user: User): TokenInfo
    fun verifyToken(token: String, isAccess: Boolean): Long
    fun refreshToken(user: User): TokenInfo
}