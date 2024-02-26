package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ValidateEmailUseCaseTest {

    @Test
    fun `Invoke calls email matcher and returns if is valid`()  {
        val emailMatcher = mockk<EmailMatcher>()
        every { emailMatcher.isValid("mail@mail.com") } returns true
        val validateEmailUseCase = ValidateEmailUseCase(emailMatcher)
        val result = validateEmailUseCase("mail@mail.com")
        assertTrue(result)
    }


}