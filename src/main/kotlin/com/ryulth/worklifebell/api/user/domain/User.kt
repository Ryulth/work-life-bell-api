package com.ryulth.worklifebell.api.user.domain

import com.ryulth.worklifebell.api.common.util.TimeUtils
import com.ryulth.worklifebell.api.common.util.TimeUtils.getNow
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * 유저의 데이터
 * @property id 유저 아이디로 사용될 auto-increment 되는 필드
 * @property publicId 사용자를 트래킹하기위해 일반 사용자에게 내려가는 공개 아이디
 * @property loginType 로그인 타입
 * @property lastLoginDateTime 마지막 로그인한 시간
 * @property deleted softDelete 를 위한 필드
 * @property deletedDateTime softDelete 된 시간
 */
@Table("user")
class User(
    @Id
    var id: Long? = null,
    val publicId: String,
    val loginType: LoginType,
    var lastLoginDateTime: LocalDateTime,
    var deleted: Boolean,
    var deletedDateTime: LocalDateTime? = null
) {
    companion object {
        fun of(publicId: String, loginType: LoginType) = User (
            publicId = publicId,
            loginType = loginType,
            lastLoginDateTime = getNow(),
            deleted = false
        )
    }
}