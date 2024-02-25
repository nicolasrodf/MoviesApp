package com.nicolasrf.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.nicolasrf.moviesapp.data.local.MovieDao
import com.nicolasrf.moviesapp.data.local.MovieEntity
import com.nicolasrf.moviesapp.data.mappers.toDomainModel
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, MovieEntity>,
    private val movieDao: MovieDao
) : MoviesRepository {
    override fun findUpcomingMovies(): Flow<PagingData<Movie>> {
        return pager
            .flow
            .map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
    }

    override fun findById(id: Int): Flow<Movie> {
        return movieDao.findById(id).map { it.toDomainModel() }
    }
}