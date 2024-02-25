package com.nicolasrf.moviesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nicolasrf.moviesapp.usecases.ValidateLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    validateLoggedInUseCase: ValidateLoggedInUseCase
) : ViewModel() {
    var isLoggedIn by mutableStateOf(validateLoggedInUseCase())
        private set
}