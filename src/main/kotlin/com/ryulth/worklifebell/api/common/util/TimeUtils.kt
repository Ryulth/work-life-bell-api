package com.ryulth.worklifebell.api.common.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

object TimeUtils {
    private val ZONE_ID_UTC: ZoneId = ZoneId.of("UTC")
    fun getNow(): LocalDateTime = LocalDateTime.now(ZONE_ID_UTC).withNano(0).withSecond(0)
    fun parseDate(dateString: String): LocalDate = LocalDate.parse(dateString)
}