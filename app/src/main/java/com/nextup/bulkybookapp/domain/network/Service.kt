package com.nextup.bulkybookapp.domain.network

import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.LoginResponse
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {

    @POST("auth/login")
    suspend fun login(@Body loginParams: LoginParams): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerParams: RegisterParams): Response<RegisterResponse>




}