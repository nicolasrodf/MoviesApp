package com.nicolasrf.moviesapp.presentation.authentication.login

import com.nicolasrf.moviesapp.data.repository.FakeAuthenticationRepository
import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher
import com.nicolasrf.moviesapp.usecases.LoginUseCases
import com.nicolasrf.moviesapp.usecases.LoginWithEmailUseCase
import com.nicolasrf.moviesapp.usecases.ValidateEmailUseCase
import com.nicolasrf.moviesapp.usecases.ValidatePasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authenticationRepository: FakeAuthenticationRepository

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authenticationRepository = FakeAuthenticationRepository()
        val usecases = LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(authenticationRepository),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(object : EmailMatcher {
                override fun isValid(email: String): Boolean {
                    return email.isNotEmpty()
                }
            })
        )
        loginViewModel = LoginViewModel(usecases, dispatcher)
    }

    @Test
    fun `initial state is empty`() {
        val state = loginViewModel.state
        assertEquals(
            LoginState(
                email = "",
                password = "",
                emailError = null,
                passwordError = null,
                isLoggedIn = false,
                isLoading = false
            ),
            state
        )
    }

    @Test
    fun `given an email, verify the state updates the email`() {
        val initialState = loginViewModel.state.email
        assertEquals(initialState, "")
        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        val updatedState = loginViewModel.state.email
        assertEquals(
            "asd@asd.com",
            updatedState
        )
    }

    @Test
    fun `given invalid email, show email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange(""))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.emailError)
    }

    @Test
    fun `set valid email, Login, no email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assert(state.emailError == null)
    }

    @Test
    fun `set invalid password, Login, show password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asd"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.passwordError)
    }

    @Test
    fun `set valid password, Login, no password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD*123"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNull(state.passwordError)
    }

    @Test
    fun `set valid details, Login, starts loading and then logs in`() = scope.runTest {
        loginViewModel.onEvent(LoginEvent.EmailChange("admin@mail.com"))
        loginViewModel.onEvent(LoginEvent.PasswordChange("Password*123"))
        loginViewModel.onEvent(LoginEvent.Login)
        var state = loginViewModel.state
        assertNull(state.passwordError)
        assertNull(state.emailError)
        assert(state.isLoading)
        advanceUntilIdle()
        state = loginViewModel.state
        assert(state.isLoggedIn)
    }
}