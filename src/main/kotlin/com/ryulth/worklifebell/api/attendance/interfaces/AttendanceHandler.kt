package com.ryulth.worklifebell.api.attendance.interfaces

import com.ryulth.worklifebell.api.attendance.application.AttendanceService
import com.ryulth.worklifebell.api.attendance.application.OffWorkRequest
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
    fun getById(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id").toLong()
        return Mono.just(id)
            .flatMap {
                attendanceService.getById(it)
            }
            .flatMap {
                ServerResponse.ok().body(fromValue(it))
            }
    }


    fun getMyAttendances(request: ServerRequest): Mono<ServerResponse> {
        val userId = 0L
        return Flux.just(userId)
            .flatMap { attendanceService.getByUserId(it) }
            .collect(toList())
            .flatMap {
                ServerResponse.ok().body(fromValue(it))
            }
    }

    fun onWork(request: ServerRequest): Mono<ServerResponse> = request.bodyToMono(OnWorkRequest::class.java)
        .flatMap {
            attendanceService.onWork(0, it)
        }.flatMap {
            ServerResponse.ok().body(fromValue(it))
        }

    fun offWork(request: ServerRequest): Mono<ServerResponse> = request.bodyToMono(OffWorkRequest::class.java)
        .flatMap {
            attendanceService.offWork(0, it)
        }.flatMap {
            ServerResponse.ok().body(fromValue(it))
        }
}