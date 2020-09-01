package com.ryulth.worklifebell.api.auth.application

import com.ryulth.worklifebell.api.user.domain.User
import org.springframework.stereotype.Service
import reactor.bool.BooleanUtils
import reactor.core.publisher.Mono

@Service
abstract class AuthService<T : RegisterRequest>(
    private val tokenProvider: TokenProvider
) {
    fun register(registerRequest: T): Mono<LoginResponse> = Mono.just(registerRequest)
            .filterWhen { BooleanUtils.not(isExistUser(it)) }
            .flatMap { registerUser(it) }
            .flatMap { user ->
                val tokenInfo = tokenProvider.generatedToken(user)
                Mono.just(
                    LoginResponse.of(
                        user.id!!,
                        user.publicId,
                        tokenInfo
                    )
                )
            }

    protected abstract fun registerUser(registerRequest: T): Mono<User>
    protected abstract fun isExistUser(registerRequest: T): Mono<Boolean>
    protected abstract fun test(registerRequest: T)
}
