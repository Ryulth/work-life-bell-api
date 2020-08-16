package com.ryulth.worklifebell.api.attendance.interfaces

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
    fun routerFunction(): RouterFunction<ServerResponse> = nest(path("/attendance"),
        router {
            listOf(
                GET("/", attendanceHandler::getAll),
                GET("/{id}", attendanceHandler::getById),
                POST("/", attendanceHandler::save),
                DELETE("/{id}", attendanceHandler::delete))
        }
    )
}