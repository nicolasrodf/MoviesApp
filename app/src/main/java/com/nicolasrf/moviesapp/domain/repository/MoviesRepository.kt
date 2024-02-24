package com.nicolasrf.moviesapp.domain.repository

import androidx.paging.PagingData
import com.nicolasrf.moviesapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun findUpcomingMovies(): Flow<PagingData<Movie>>
}