package com.daniela.sentrii.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.daniela.sentrii.R

@Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    var registroExitoso by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.sentri_logo),
            contentDescription = "Logo Sentri",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        // Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", color = Color.White) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors()
        )

        // Correo
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo o Teléfono", color = Color.White) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors()
        )

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors()
        )

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmarPassword,
            onValueChange = { confirmarPassword = it },
            label = { Text("Confirmar Contraseña", color = Color.White) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (error != null) {
            Text(error ?: "", color = Color.Red, fontSize = 12.sp)
        } else if (registroExitoso) {
            Text("Registro exitosamente guardado", color = Color.Green, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nombre.trim().isEmpty() || correo.trim().isEmpty() || password.trim().isEmpty() || confirmarPassword.trim().isEmpty()) {
                error = "Todos los campos son obligatorios"
                registroExitoso = false
            } else if (password != confirmarPassword) {
                error = "Las contraseñas no coinciden"
                registroExitoso = false
            } else {
                error = null
                registroExitoso = true
            }
        }) {
            Text("Registrarte")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Volver al inicio de sesión",
            color = Color.Gray,
            modifier = Modifier
                .clickable { navController.navigate("login") }
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun fieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.DarkGray,
    unfocusedContainerColor = Color.DarkGray,
    focusedIndicatorColor = Color.Gray,
    unfocusedIndicatorColor = Color.DarkGray,
    cursorColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.White
)
