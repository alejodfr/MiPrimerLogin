package com.arepasinqueso.miprimerlogin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.arepasinqueso.miprimerlogin.data.LoginRequest
import com.arepasinqueso.miprimerlogin.data.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(){
    var uiState by remember { mutableStateOf<LoginUiState>(LoginUiState.Idle) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            // desactivar el auto completado del teclado
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )



        Button(onClick = {
            scope.launch {
                uiState = LoginUiState.Loading

                try {
                    val response = RetrofitInstance.api.login(LoginRequest(username, password))
                    uiState = LoginUiState.Success(response)
                } catch (e: Exception) {
                    uiState = LoginUiState.Error(e.message ?: "Error desconocido")
                }

            }
        }) {
            Text("Entrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}