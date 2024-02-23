package com.nicolasrf.moviesapp.domain.repository

import arrow.core.Either
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.model.Error

interface MoviesRepository {

    suspend fun findUpcomingMovies(page: Int): Either<Error, List<Movie>>

}