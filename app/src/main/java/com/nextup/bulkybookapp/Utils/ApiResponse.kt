package com.nextup.bulkybookapp.Utils

import retrofit2.Response

sealed class ApiResponse <T> (val data: T? = null, val message: String? = null, val response: Response<T>? = null) {

    class ApiSuccess<T>(data: T?, message: String, response: Response<T>?) : ApiResponse<T>(data = data, message = message, response = response)
    class ApiFailed<T>(message: String, response: Response<T>?) : ApiResponse<T>(message = message, response = response)


}
