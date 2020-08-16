package com.ryulth.worklifebell.api.attendance.interfaces

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AttendanceHandler() {
    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        return Mono.empty()
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        println(request.pathVariable("id").toLong())
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(request.bodyToMono(String::class.java), String::class.java)
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(request.bodyToMono(String::class.java), String::class.java)
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        return Mono.empty()
    }
}