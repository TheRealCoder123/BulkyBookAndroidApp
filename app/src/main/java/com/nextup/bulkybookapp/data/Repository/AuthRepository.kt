package com.nextup.bulkybookapp.data.Repository

import com.nextup.bulkybookapp.Utils.ApiResponse
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.LoginResponse
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterResponse
import com.nextup.bulkybookapp.domain.network.Service

class AuthRepository(
    private val service: Service
) : IAuthRepository{


    override suspend fun register(params: RegisterParams): ApiResponse<RegisterResponse> {

        val response = service.register(params)
        return if (response.isSuccessful){
            ApiResponse.ApiSuccess(
                response.body(),
                response.message(),
                response
            )
        }else{
            ApiResponse.ApiFailed(
                response.message(),
                response
            )
        }

    }

    override suspend fun login(params: LoginParams): ApiResponse<LoginResponse> {

        val response = service.login(params)

        return if (response.isSuccessful){

            ApiResponse.ApiSuccess(
                response.body(),
                response.message(),
                response
            )

        }else {

            ApiResponse.ApiFailed(
                response.message(),
                response
            )

        }

    }


}