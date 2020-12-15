package com.ryulth.worklifebell.api.auth.web

import com.ryulth.worklifebell.api.auth.service.EmailAuthService
import com.ryulth.worklifebell.api.auth.service.EmailRegisterRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AuthHandler(
    private val emailAuthService: EmailAuthService
) {
    fun registerWithEmail(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(EmailRegisterRequest::class.java)
            .flatMap {
                emailAuthService.register(it)
            }.flatMap {
                ServerResponse.ok().body(BodyInserters.fromValue(it))
            }
    }

    fun loginWithEmail(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty();
    }
}