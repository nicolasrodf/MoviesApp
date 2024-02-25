package com.nicolasrf.moviesapp.data.repository

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthenticationRepository : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Flow<Result<Unit>> {
        return flow{
            emit(
                Result.success(Unit)
            )
        }
    }

    override suspend fun logout(): Flow<Boolean> {
        return flow{
            emit(true)
        }
    }

    override val isLoggedIn: Boolean
        get() = false
}
