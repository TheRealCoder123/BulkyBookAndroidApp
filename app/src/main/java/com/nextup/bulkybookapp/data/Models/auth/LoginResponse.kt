package com.nextup.bulkybookapp.data.Models.auth

data class LoginResponse(
    val message: String,
    val status: Int,
    val success: Boolean,
    val token: String,
    val user: User
)