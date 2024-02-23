package com.nicolasrf.moviesapp.data.repository

import android.content.SharedPreferences
import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticationRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : AuthenticationRepository {

    override suspend fun login(email: String, password: String): Flow<Result<Unit>> {
        return flow {
            delay(2000)
            if (email == "admin@mail.com" && password == "Password*123") {
                sharedPreferences.edit().putBoolean(IS_LOGGED_IN, true).apply()
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("User invalid")))
            }
        }
    }

    override suspend fun logout() : Flow<Boolean> {
        return flow {
            delay(500)
            sharedPreferences.edit().putBoolean(IS_LOGGED_IN, false).apply()
            emit(true)
        }
    }

    override val isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

    companion object {
        private const val IS_LOGGED_IN = "is_logged_in"
    }
}