package com.nicolasrf.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolasrf.moviesapp.usecases.LogoutUseCase
import com.nicolasrf.moviesapp.usecases.ValidateLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val validateLoggedInUseCase: ValidateLoggedInUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    var isLoggedIn by mutableStateOf(validateLoggedInUseCase())
        private set

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}