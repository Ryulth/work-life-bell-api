package com.ryulth.worklifebell.api.user.domain

import com.ryulth.worklifebell.api.user.domain.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: ReactiveCrudRepository<User, Long>