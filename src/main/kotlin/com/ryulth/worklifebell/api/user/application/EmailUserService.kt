package com.ryulth.worklifebell.api.user.application

import com.ryulth.worklifebell.api.attendance.domain.Attendance
import com.ryulth.worklifebell.api.user.domain.EmailUser
import com.ryulth.worklifebell.api.user.infrastructure.EmailUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class EmailUserService(
    private val emailUserRepository: EmailUserRepository
) {
    @Transactional
    fun create(userId: Long, email: String, password: String): Mono<EmailUser> =
        save(EmailUser.of(userId, email, password))

    @Transactional(readOnly = true)
    fun existByEmail(email: String): Mono<Boolean> = emailUserRepository.existByEmail(email)

    private fun save(emailUser: EmailUser) = Mono.just(emailUser)
        .flatMap { emailUserRepository.save(it) }

    private fun findById(id: Long) = Mono.just(id)
        .flatMap { emailUserRepository.findById(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))

    private fun findByEmail(email: String) = Mono.just(email)
        .flatMap { emailUserRepository.findByEmail(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))
}