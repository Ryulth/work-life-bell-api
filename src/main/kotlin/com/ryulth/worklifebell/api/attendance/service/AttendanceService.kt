package com.ryulth.worklifebell.api.attendance.service

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import com.ryulth.worklifebell.api.attendance.domain.AttendanceRepository
import com.ryulth.worklifebell.api.common.util.TimeUtils.getNow
import com.ryulth.worklifebell.api.common.util.TimeUtils.parseDate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
class AttendanceService(
    private val attendanceRepository: AttendanceRepository
) {
    @Transactional
    fun create(userId: Long, localDate: LocalDate): Mono<Attendance> = save(Attendance.of(userId, localDate))

    @Transactional
    fun onWork(userId: Long, onWorkRequest: OnWorkRequest): Mono<Attendance> {
        val localDate = parseDate(onWorkRequest.workDate)
        return findByUserIdAndWorkDate(userId, localDate)
            .switchIfEmpty(create(userId, localDate))
            .flatMap {
                Mono.fromCallable {
                    it.onWorkDateTime = getNow()
                    save(it)
                }.then(Mono.just(it))
            }
    }

    @Transactional
    fun offWork(userId: Long, offWorkRequest: OffWorkRequest): Mono<Attendance> {
        val localDate = parseDate(offWorkRequest.workDate)
        return findByUserIdAndWorkDate(userId, localDate)
            .switchIfEmpty(create(userId, localDate))
            .flatMap {
                Mono.fromCallable {
                    it.offWorkDateTime = getNow()
                    save(it)
                }.then(Mono.just(it))
            }
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): Mono<Attendance> = findById(id)

    @Transactional(readOnly = true)
    fun getByUserId(userId: Long): Flux<Attendance> = findAllByUserId(userId)

    private fun save(attendance: Attendance) = Mono.just(attendance)
        .flatMap { attendanceRepository.save(it) }

    private fun findById(id: Long) = Mono.just(id)
        .flatMap { attendanceRepository.findById(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))

    private fun findAllByUserId(userId: Long) = Flux.just(userId)
        .flatMap { attendanceRepository.findAllByUserId(it) }
        .switchIfEmpty(Flux.error(IllegalArgumentException()))

    private fun findByUserIdAndWorkDate(userId: Long, workDate: LocalDate) = Mono.just(userId)
        .flatMap { attendanceRepository.findByUserIdAndWorkDate(userId, workDate) }
}