package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository

class ValidateLoggedInUseCase(private val repository: AuthenticationRepository) {
    operator fun invoke() : Boolean{
        return repository.isLoggedIn
    }
}
