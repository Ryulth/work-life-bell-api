package com.ryulth.worklifebell.api.auth.service

data class EmailRegisterRequest(
    val email: String,
    val password: String
) : RegisterRequest()
