package com.ryulth.worklifebell.api.attendance.domain

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface AttendanceRepository: ReactiveCrudRepository<Attendance, Long> {
    fun findByUserIdAndWorkDate(userId: Long, workDate: LocalDate): Mono<Attendance>
    fun findAllByUserId(userId: Long): Flux<Attendance>
}

