package com.ryulth.worklifebell.api.user.infrastructure

import com.ryulth.worklifebell.api.user.domain.EmailUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EmailUserRepository: ReactiveCrudRepository<EmailUser, Long> {
    fun findByEmail(email: String): Mono<EmailUser>
    fun existByEmail(email: String): Mono<Boolean>
}