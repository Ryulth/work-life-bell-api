package com.ryulth.worklifebell.api.auth.service

import com.ryulth.worklifebell.api.user.service.EmailUserService
import com.ryulth.worklifebell.api.user.service.UserService
import com.ryulth.worklifebell.api.user.domain.LoginType
import com.ryulth.worklifebell.api.user.domain.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmailAuthService(
    private val userService: UserService,
    private val emailUserService: EmailUserService,
    tokenProvider: TokenProvider
) : AuthService<EmailRegisterRequest>(tokenProvider) {
    companion object {
        private val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        private const val EMAIL_POSTFIX: String = "@EMAIL"
        private fun generatePublicId(email: String) = "email$EMAIL_POSTFIX"
    }

    override fun registerUser(registerRequest: EmailRegisterRequest): Mono<User> = Mono.just(registerRequest)
        .flatMap {
            userService.create(generatePublicId(registerRequest.email), LoginType.EMAIL)
                .doOnSuccess { user ->
                    emailUserService.create(
                        user.id!!,
                        registerRequest.email,
                        passwordEncoder.encode(registerRequest.password)
                    )
                }
        }

    override fun isExistUser(registerRequest: EmailRegisterRequest): Mono<Boolean> =
        emailUserService.existByEmail(registerRequest.email)

    override fun test(registerRequest: EmailRegisterRequest) {
        TODO("Not yet implemented")
    }
}