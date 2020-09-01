package com.ryulth.worklifebell.api.auth.interfaces

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class AuthRouter(
    private val authHandler: AuthHandler
) {
    @Bean
    fun authRouterFunction(): RouterFunction<ServerResponse> =
        RouterFunctions.nest(RequestPredicates.path("/auth"),
            router {
                listOf(
                    POST("/register/email", authHandler::registerWithEmail),
                    POST("/login/email", authHandler::loginWithEmail)
                )
            }
        )
}