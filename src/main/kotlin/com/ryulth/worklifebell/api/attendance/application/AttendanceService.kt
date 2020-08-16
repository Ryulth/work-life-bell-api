package com.ryulth.worklifebell.api.attendance.application

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import com.ryulth.worklifebell.api.attendance.infrastructure.AttendanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AttendanceService(
    private val attendanceRepository: AttendanceRepository
) {
    @Transactional
    fun create(onWorkRequest: OnWorkRequest) = Mono.just(onWorkRequest)
        .flatMap { save(Attendance.of(0, onWorkRequest)) }

    @Transactional
    fun getByUserId(userId: Long) = findAllByUserId(userId)

    private fun save(attendance: Attendance) = Mono.just(attendance)
        .flatMap { attendanceRepository.save(it) }

    private fun findById(id: Long) = Mono.just(id)
        .flatMap { attendanceRepository.findById(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))

    private fun findAllByUserId(id: Long) = Flux.just(id)
        .flatMap { attendanceRepository.findAllByUserId(it) }
        .switchIfEmpty(Flux.error(IllegalArgumentException()))

}