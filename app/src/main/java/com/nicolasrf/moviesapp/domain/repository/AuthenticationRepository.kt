package com.nicolasrf.moviesapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Flow<Result<Unit>>
    suspend fun logout() : Flow<Boolean>
    val isLoggedIn : Boolean
}