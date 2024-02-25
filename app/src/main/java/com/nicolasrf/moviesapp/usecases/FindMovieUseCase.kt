package com.nicolasrf.moviesapp.usecases

import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindMovieUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Movie> = repository.findById(id)
}