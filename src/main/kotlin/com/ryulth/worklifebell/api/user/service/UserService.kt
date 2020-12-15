package com.ryulth.worklifebell.api.user.service

import com.ryulth.worklifebell.api.user.domain.LoginType
import com.ryulth.worklifebell.api.user.domain.User
import com.ryulth.worklifebell.api.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun create(publicId: String, loginType: LoginType): Mono<User> =
        save(User.of(publicId, loginType))

    private fun save(user: User) = Mono.just(user)
        .flatMap { userRepository.save(it) }

    private fun findById(id: Long) = Mono.just(id)
        .flatMap { userRepository.findById(it) }
        .switchIfEmpty(Mono.error(IllegalArgumentException()))
}