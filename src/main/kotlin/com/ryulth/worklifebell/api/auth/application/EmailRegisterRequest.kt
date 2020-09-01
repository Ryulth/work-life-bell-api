package com.ryulth.worklifebell.api.auth.application

data class EmailRegisterRequest(
    val email: String,
    val password: String
) : RegisterRequest()
