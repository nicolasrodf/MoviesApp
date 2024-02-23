package com.nicolasrf.moviesapp.ui.authentication.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val authError: String? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)
