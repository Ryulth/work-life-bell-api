package com.ryulth.worklifebell.api.attendance.domain

import com.ryulth.worklifebell.api.attendance.application.OnWorkRequest
import com.ryulth.worklifebell.api.common.util.TimeUtils.getNow
import com.ryulth.worklifebell.api.common.util.TimeUtils.parseDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 출근 관련 기록 데이터
 */
@Table("Attendance")
class Attendance(
    @Id
    var id: Long? = null,
    val userId: Long,
    val workDate: LocalDate,
    var onWorkDateTime: LocalDateTime? = null,
    var offWorkDateTime: LocalDateTime? = null
) {
    companion object {
        fun of(userId: Long, onWorkRequest: OnWorkRequest) = Attendance (
            userId = userId,
            workDate = parseDate(onWorkRequest.workDate),
            onWorkDateTime = getNow()
        )
    }
}