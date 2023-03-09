package com.nextup.bulkybookapp.data.Repository

import com.nextup.bulkybookapp.Utils.ApiResponse
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.LoginResponse
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterResponse

interface IAuthRepository {

    suspend fun register(params: RegisterParams): ApiResponse<RegisterResponse>
    suspend fun login(params: LoginParams): ApiResponse<LoginResponse>

}