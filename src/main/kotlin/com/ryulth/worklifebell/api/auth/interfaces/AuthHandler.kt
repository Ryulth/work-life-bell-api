package com.ryulth.worklifebell.api.auth.interfaces

import com.ryulth.worklifebell.api.auth.application.EmailAuthService
import com.ryulth.worklifebell.api.auth.application.EmailRegisterRequest
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