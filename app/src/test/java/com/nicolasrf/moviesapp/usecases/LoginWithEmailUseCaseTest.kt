package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginWithEmailUseCaseTest {
    @Test
    fun `Invoke calls authentication repository`() = runBlocking {
        val authenticationRepository = mockk<AuthenticationRepository>(relaxed = true)
        val loginWithEmailUseCase = LoginWithEmailUseCase(authenticationRepository)
        loginWithEmailUseCase("email","password")
        coVerify { authenticationRepository.login("email","password") }
    }
}