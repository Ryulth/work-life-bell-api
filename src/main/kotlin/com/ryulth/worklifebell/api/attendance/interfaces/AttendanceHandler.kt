package com.ryulth.worklifebell.api.attendance.interfaces

import com.ryulth.worklifebell.api.attendance.application.AttendanceService
import com.ryulth.worklifebell.api.attendance.application.OnWorkRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.stream.Collectors.toList

@Component
class AttendanceHandler(
    private val attendanceService: AttendanceService
) {
    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        return Mono.empty()
    }

    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return Flux.just(id)
            .flatMap { attendanceService.getByUserId(it) }
            .collect(toList())
            .flatMap {
                ServerResponse.ok().body(fromValue(it))
            }
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        val onWorkRequest = request.bodyToMono(OnWorkRequest::class.java)
        return onWorkRequest.flatMap {
            attendanceService.create(it)
        }.flatMap {
            ServerResponse.ok().body(fromValue(it))
        }
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        println(request)
        return Mono.empty()
    }
}