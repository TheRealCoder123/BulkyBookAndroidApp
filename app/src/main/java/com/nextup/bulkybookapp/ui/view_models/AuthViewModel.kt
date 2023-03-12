package com.nextup.bulkybookapp.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextup.bulkybookapp.Utils.ApiResponse
import com.nextup.bulkybookapp.Utils.DataStore
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
    private var networkConnection: NetworkConnection,
    private var dataStore: DataStore
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
                    when(response.response?.code()){
                        409 -> {
                            _registerState.emit(UiState.Error(
                                null,
                                "A user with that email already exists into our database"
                            ))
                        }
                        400 -> {
                            _registerState.emit(UiState.Error(
                                null,
                                "Something went wrong creating your account, please try again"
                            ))
                        }
                    }
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

                    when(response.response?.code()){

                        404->{
                            _loginState.emit(UiState.Error(null,"User with those credentials does not exist"))
                        }
                        400->{
                            _loginState.emit(UiState.Error(
                                null,
                                "Your email or password does not match to any into our database"
                            ))
                        }


                    }


                }
                is ApiResponse.ApiSuccess -> {

                    val data = response.data
                    dataStore.saveString(dataStore.tags().USER_IDENTIFIER_TAG, data!!.user.userId)
                    dataStore.saveString(dataStore.tags().TOKEN_TAG, data.token)
                    dataStore.saveString(dataStore.tags().USER_ROLE_TAG, data.user.role)

                    _loginState.emit(UiState.Success(response.data))
                }
            }

        }else{
            _loginState.emit(UiState.Error(null, message = "No internet connection"))
        }

    }


}