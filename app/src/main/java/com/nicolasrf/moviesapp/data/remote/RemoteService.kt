package com.nicolasrf.moviesapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("movie/upcoming")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): RemoteResult
}