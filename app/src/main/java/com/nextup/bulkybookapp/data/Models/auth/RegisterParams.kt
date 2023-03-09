package com.nextup.bulkybookapp.data.Models.auth

data class RegisterParams(
    val email: String,
    val lastname: String,
    val name: String,
    val password: String
)