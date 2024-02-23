package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository

class LogoutUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}
