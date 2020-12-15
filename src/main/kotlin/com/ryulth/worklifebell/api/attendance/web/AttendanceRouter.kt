package com.ryulth.worklifebell.api.attendance.web

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Component
class AttendanceRouter(
    private val attendanceHandler: AttendanceHandler
) {
    @Bean
    fun attendanceRouterFunction(): RouterFunction<ServerResponse> = nest(path("/api/attendances"),
        router {
            listOf(
                GET("/{id}", attendanceHandler::getById),
                GET("/users", attendanceHandler::getMyAttendances),
                POST("/on", attendanceHandler::onWork),
                POST("/off", attendanceHandler::offWork)
            )
        }
    )
}