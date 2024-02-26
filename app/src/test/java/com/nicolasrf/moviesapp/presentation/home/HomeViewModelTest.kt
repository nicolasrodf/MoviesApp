package com.nicolasrf.moviesapp.presentation.home

import com.nicolasrf.moviesapp.testrules.CoroutinesTestRule
import com.nicolasrf.moviesapp.usecases.GetUpcomingMoviesUseCase
import com.nicolasrf.moviesapp.usecases.LogoutUseCase
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: HomeViewModel

    @Test
    fun `UI initial state is correct`() = runTest {
        val getUpcomingMoviesUseCase = mockk<GetUpcomingMoviesUseCase>(relaxed = true)
        val logoutUseCase = mockk<LogoutUseCase>(relaxed = true)
        vm = HomeViewModel(
            getUpcomingMoviesUseCase,
            logoutUseCase
        )
        assertEquals(vm.state, HomeState(isLogout = false, isLoading = false))
    }
}