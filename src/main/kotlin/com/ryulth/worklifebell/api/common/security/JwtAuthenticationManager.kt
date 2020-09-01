package com.ryulth.worklifebell.api.common.security

import com.ryulth.worklifebell.api.auth.application.TokenProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager(
    private val tokenProvider: TokenProvider
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
            .map { tokenProvider.verifyToken(it.credentials as String, true) }
            .onErrorResume { Mono.empty() }
            .map { userId ->
                UsernamePasswordAuthenticationToken(
                    userId,
                    authentication.credentials as String,
                    mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
                )
            }
    }
}