package com.ryulth.worklifebell.api.auth

import com.ryulth.worklifebell.api.security.JwtToken
import com.ryulth.worklifebell.api.security.JwtTokenProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(
    private val tokenProvider: JwtTokenProvider
) {
    @GetMapping("/auth/test")
    fun testToken(): Mono<JwtToken> {
        return Mono.just(tokenProvider.generatedToken(0L))
    }
}