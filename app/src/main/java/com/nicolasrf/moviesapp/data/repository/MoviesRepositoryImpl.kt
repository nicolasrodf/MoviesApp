package com.nicolasrf.moviesapp.data.repository

import arrow.core.Either
import com.nicolasrf.moviesapp.data.remote.RemoteMovie
import com.nicolasrf.moviesapp.data.remote.RemoteService
import com.nicolasrf.moviesapp.data.tryCall
import com.nicolasrf.moviesapp.di.ApiKey
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.model.Error
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    @ApiKey private val apiKey: String,
    private val remoteService: RemoteService
): MoviesRepository {

    override suspend fun findUpcomingMovies(page: Int): Either<Error, List<Movie>> = tryCall {
        remoteService
            .listPopularMovies(apiKey, page)
            .results
            .toDomainModel()
    }

    private fun List<RemoteMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

    private fun RemoteMovie.toDomainModel(): Movie =
        Movie(
            id,
            title,
            overview,
            releaseDate,
            "https://image.tmdb.org/t/p/w500/$posterPath",
            voteAverage
        )

}