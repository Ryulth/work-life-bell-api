package com.ryulth.worklifebell.api.attendance.infrastructure

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface AttendanceRepository: ReactiveCrudRepository<Attendance, Long> {
    fun findByUserId(userId: Long): Mono<Attendance>
}

