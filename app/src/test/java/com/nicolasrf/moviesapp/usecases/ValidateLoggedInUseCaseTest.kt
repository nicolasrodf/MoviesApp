package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test

class ValidateLoggedInUseCaseTest {
    @Test
    fun `Invoke calls authentication repository`()  {
        val authenticationRepository = mockk<AuthenticationRepository>(relaxed = true)
        val validateLoggedInUseCase = ValidateLoggedInUseCase(authenticationRepository)
        validateLoggedInUseCase()
        coVerify { authenticationRepository.isLoggedIn }
    }
}