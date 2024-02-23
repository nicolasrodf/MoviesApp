package com.nicolasrf.moviesapp.data.repository

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl : AuthenticationRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return if(email == "admin@mail.com" && password == "Password*123"){
            Result.success(Unit)
        }else{
            Result.failure(Exception("User invalid"))
        }
    }

    override suspend fun logout() {
    }
}