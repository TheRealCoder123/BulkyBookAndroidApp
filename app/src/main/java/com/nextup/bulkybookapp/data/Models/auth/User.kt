package com.nextup.bulkybookapp.data.Models.auth

data class User(
    val address: String,
    val email: String,
    val lastname: String,
    val name: String,
    val phoneNumber: String,
    val role: String,
    val userId: String
)