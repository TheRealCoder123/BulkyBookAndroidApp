package com.nextup.bulkybookapp.ui.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextup.bulkybookapp.Utils.ApiResponse
import com.nextup.bulkybookapp.Utils.UiState
import com.nextup.bulkybookapp.data.Models.auth.LoginParams
import com.nextup.bulkybookapp.data.Models.auth.LoginResponse
import com.nextup.bulkybookapp.data.Models.auth.RegisterParams
import com.nextup.bulkybookapp.data.Models.auth.RegisterResponse
import com.nextup.bulkybookapp.data.Repository.AuthRepository
import com.nextup.bulkybookapp.domain.network.NetworkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private var repository: AuthRepository,
    private var networkConnection: NetworkConnection
) : ViewModel() {

    private val _registerState = MutableStateFlow<UiState<RegisterResponse>>(UiState.Idle())
    val registerState = _registerState.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle())
    val loginState = _loginState.asStateFlow()

    fun register(registerParams: RegisterParams) = viewModelScope.launch {
        _registerState.emit(UiState.Loading())

        if (networkConnection.check()){

            when(val response = repository.register(registerParams)){
                is ApiResponse.ApiFailed -> {
                    _registerState.emit(UiState.Error(null,response.message ?: response.response!!.message()))
                }
                is ApiResponse.ApiSuccess -> {
                    _registerState.emit(UiState.Success(response.data))
                }
            }

        }else{
            _registerState.emit(UiState.Error(null, message = "No internet connection"))
        }

    }

    fun login(loginParams: LoginParams) = viewModelScope.launch {
        _loginState.emit(UiState.Loading())

        if (networkConnection.check()){

            when(val response = repository.login(params = loginParams)){
                is ApiResponse.ApiFailed -> {
                    _loginState.emit(UiState.Error(null,response.message ?: response.response!!.message()))
                }
                is ApiResponse.ApiSuccess -> {
                    _loginState.emit(UiState.Success(response.data))
                    Log.e("login error", "${response.response?.errorBody()}")
                }
            }

        }else{
            _loginState.emit(UiState.Error(null, message = "No internet connection"))
        }

    }


}