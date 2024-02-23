package com.nicolasrf.moviesapp.ui.util

import com.nicolasrf.moviesapp.usecases.PasswordResult

object PasswordErrorParser {
    fun parseError(error: PasswordResult): String? {
        return when (error) {
            PasswordResult.VALID -> null
            PasswordResult.INVALID_LOWERCASE -> "La contraseña debe tener al menos 1 caracter en minuscula"
            PasswordResult.INVALID_UPPERCASE -> "La contraseña debe tener al menos 1 caracter en mayuscula"
            PasswordResult.INVALID_DIGITS -> "La contraseña debe tener al menos 1 numero"
            PasswordResult.INVALID_LENGTH -> "La contraseña debe tener al menos 8 caracteres"
            PasswordResult.INVALID_SPECIAL -> "La contraseña debe tener al menos 1 caracter especial"
        }
    }
}