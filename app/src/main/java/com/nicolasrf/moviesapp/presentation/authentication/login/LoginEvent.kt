package com.nicolasrf.moviesapp.presentation.authentication.login

sealed interface LoginEvent {
    data class EmailChange(val email: String) : LoginEvent
    data class PasswordChange(val password: String) : LoginEvent
    data object Login : LoginEvent
}
