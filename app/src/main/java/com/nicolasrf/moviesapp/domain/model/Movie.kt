package com.nicolasrf.moviesapp.domain.model

data class Movie (
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double
)