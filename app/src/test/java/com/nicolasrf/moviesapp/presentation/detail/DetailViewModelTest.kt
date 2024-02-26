package com.nicolasrf.moviesapp.presentation.detail

import app.cash.turbine.test
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.testrules.CoroutinesTestRule
import com.nicolasrf.moviesapp.usecases.FindMovieUseCase
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    private val movie = Movie(
        1,
        "",
        "",
        "",
        "",
        5.0
    )

    @Test
    fun `UI is updated with movie on start if exists in local database`() = runTest {
        val findMovieUseCase = mockk<FindMovieUseCase>()
        vm = DetailViewModel(
            1,
            findMovieUseCase
        )
        every { findMovieUseCase(1) } returns flowOf(movie)
        vm.state.test {
            assertEquals(DetailViewModel.UiState(), awaitItem())
            assertEquals(DetailViewModel.UiState(movie = movie), awaitItem())
            cancel()
        }
    }

}