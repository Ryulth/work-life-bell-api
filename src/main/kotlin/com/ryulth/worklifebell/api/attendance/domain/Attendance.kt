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
 * @property id auto-increment 되는 아이디
 * @property userId 출근을 한 유저 아이디
 * @property workDate 출근을 하는 날짜
 * @property onWorkDateTime 출근을 하는 시각
 * @property offWorkDateTime 퇴근을 하는 시각
 *
 * @see (userId, workDate) 조합으로 UNIQUE KEY 사용한다.
 */
@Table("attendance")
class Attendance(
    @Id
    var id: Long? = null,
    val userId: Long,
    val workDate: LocalDate,
    var onWorkDateTime: LocalDateTime? = null,
    var offWorkDateTime: LocalDateTime? = null
) {
    companion object {
        fun of(userId: Long, workDate: LocalDate) = Attendance (
            userId = userId,
            workDate = workDate
        )
    }
}