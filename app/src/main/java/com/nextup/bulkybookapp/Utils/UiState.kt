package com.nextup.bulkybookapp.Utils

sealed class UiState <T> (val data: T? = null, val message: String? = null){

    class Idle<T>() : UiState<T>()
    class Loading<T>(data: T? = null, message: String? = null) : UiState<T>(data, message)
    class Success<T>(data: T?, message: String? = null) : UiState<T>(data, message)
    class Error<T>(data: T? = null, message: String) : UiState<T>(data, message)

}
