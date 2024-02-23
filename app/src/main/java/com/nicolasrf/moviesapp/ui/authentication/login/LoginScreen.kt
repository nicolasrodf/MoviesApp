package com.nicolasrf.moviesapp.ui.authentication.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolasrf.moviesapp.ui.authentication.login.components.LoginForm

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLogin()
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        LoginForm(state = state, onEvent = viewModel::onEvent)
    }
}