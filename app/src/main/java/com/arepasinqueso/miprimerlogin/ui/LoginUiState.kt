package com.arepasinqueso.miprimerlogin.ui

import com.arepasinqueso.miprimerlogin.data.LoginResponse

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val response: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}