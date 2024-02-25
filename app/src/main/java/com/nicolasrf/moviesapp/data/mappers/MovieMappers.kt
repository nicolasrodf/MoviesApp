package com.nicolasrf.moviesapp.data.mappers

import com.nicolasrf.moviesapp.data.local.MovieEntity
import com.nicolasrf.moviesapp.data.remote.RemoteMovie
import com.nicolasrf.moviesapp.domain.model.Movie

fun RemoteMovie.toEntityModel(): MovieEntity =
    MovieEntity(
        title = title,
        overview = overview,
        releaseDate =  releaseDate,
        posterPath = "https://image.tmdb.org/t/p/w500/$posterPath",
        voteAverage = voteAverage
    )


fun MovieEntity.toDomainModel(): Movie =
    Movie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        voteAverage
    )