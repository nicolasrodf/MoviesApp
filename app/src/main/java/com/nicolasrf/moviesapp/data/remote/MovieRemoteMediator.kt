package com.nicolasrf.moviesapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nicolasrf.moviesapp.data.local.MovieDatabase
import com.nicolasrf.moviesapp.data.local.MovieEntity
import com.nicolasrf.moviesapp.data.mappers.toMovieEntity
import com.nicolasrf.moviesapp.di.ApiKey
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val movieDb: MovieDatabase,
    private val movieApi: RemoteService,
    @ApiKey private val apiKey: String
): RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val movies = movieApi.listPopularMovies(
                apiKey,
                page = loadKey
            )

            movieDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    movieDb.movieDao().clearAll()
                }
                val movieEntities = movies.results.map { it.toMovieEntity() }
                movieDb.movieDao().insertAll(movieEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = movies.results.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}