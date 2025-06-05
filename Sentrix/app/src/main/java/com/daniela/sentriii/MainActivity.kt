package com.daniela.sentriii

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            // Validaciones
            when {
                name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    showToast("Por favor, completa todos los campos.")
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showToast("Correo electrónico inválido.")
                }
                !phone.matches(Regex("^[0-9]{8,15}\$")) -> {
                    showToast("Número de teléfono inválido. Usa solo dígitos (mínimo 8).")
                }
                password.length < 6 || !password.matches(".*[A-Z].*".toRegex())
                        || !password.matches(".*[a-z].*".toRegex())
                        || !password.matches(".*\\d.*".toRegex()) -> {
                    showToast("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y un número.")
                }
                password != confirmPassword -> {
                    showToast("Las contraseñas no coinciden.")
                }
                else -> {
                    showToast("¡Registro exitoso!")
                    // Aquí va firebase
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

