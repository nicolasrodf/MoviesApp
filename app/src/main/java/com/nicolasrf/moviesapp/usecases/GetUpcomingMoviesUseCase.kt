package com.nicolasrf.moviesapp.usecases

import androidx.paging.PagingData
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke() : Flow<PagingData<Movie>> {
        return moviesRepository.findUpcomingMovies()
    }
}
