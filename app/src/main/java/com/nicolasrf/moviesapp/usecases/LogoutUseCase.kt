package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class LogoutUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() : Flow<Boolean> {
        return repository.logout()
    }
}
