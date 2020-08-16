package com.ryulth.worklifebell.api.attendance.application

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import com.ryulth.worklifebell.api.attendance.infrastructure.AttendanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class AttendanceService(
    private val attendanceRepository: AttendanceRepository
) {
    @Transactional
    fun create(onWorkRequest: OnWorkRequest) {
//        return Mono.just(onWorkRequest)
//            .filter { validateDate(it.workDate) }
//            .map {  }
//            .map { save(it) }
    }

    private fun validateDate(date: String) = true

    private fun save(attendance: Attendance) = Mono.just(attendance)
        .map { attendanceRepository.save(it) }

    private fun findById(id: Long) = Mono.just(id)
        .map { attendanceRepository.findById(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))

    private fun findByUserId(id: Long) = Mono.just(id)
        .map { attendanceRepository.findByUserId(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))

}