package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test

class GetUpcomingMoviesUseCaseTest {

    @Test
    fun `Invoke calls movies repository`()  {
        val moviesRepository = mockk<MoviesRepository>(relaxed = true)
        val getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(moviesRepository)
        getUpcomingMoviesUseCase()
        coVerify { moviesRepository.findUpcomingMovies() }
    }
}