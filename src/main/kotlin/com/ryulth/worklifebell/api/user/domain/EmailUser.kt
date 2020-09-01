package com.ryulth.worklifebell.api.user.domain

import com.ryulth.worklifebell.api.common.util.TimeUtils.getNow
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * 이메일로 로그인한 유저의 데이터
 * @property userId 이메일로 로그인한 유저 아이디
 * @property email 이메일
 * @property password 비밀번호
 * @property passwordFailCount 비밀번호 틀린 횟수
 * @property passwordChangeDateTime 비밀번호 변경된 시각
 */
@Table("email_user")
class EmailUser (
    @Id
    val userId: Long,
    val email: String,
    val password: String,
    var passwordFailCount: Int,
    var passwordChangeDateTime: LocalDateTime
) {
    companion object {
        fun of(userId: Long, email: String, password: String) = EmailUser (
            userId = userId,
            email = email,
            password = password,
            passwordFailCount = 0,
            passwordChangeDateTime = getNow()
        )
    }
}