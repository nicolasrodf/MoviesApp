package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository

class LoginWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }
}
