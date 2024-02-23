package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class LoginWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Result<Unit>> {
        return repository.login(email, password)
    }
}
