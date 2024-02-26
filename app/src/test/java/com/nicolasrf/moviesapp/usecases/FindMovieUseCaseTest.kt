package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class FindMovieUseCaseTest {

    @Test
    fun `Invoke calls movies repository and returns a movie`() {

        val movie = flowOf(Movie(
            1,
            "",
            "",
            "",
            "",
            5.0
        ))
        val moviesRepository = mockk<MoviesRepository>()
        val findMovieUseCase = FindMovieUseCase(moviesRepository)
        every { moviesRepository.findById(1) } returns movie

        val result = findMovieUseCase(1)

        assertEquals(movie, result)

    }

}