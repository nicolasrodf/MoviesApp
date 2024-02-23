package com.nicolasrf.moviesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolasrf.moviesapp.data.remote.RemoteService
import com.nicolasrf.moviesapp.di.ApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteService: RemoteService,
    @ApiKey private val apiKey: String
) : ViewModel() {
        init {
            viewModelScope.launch {
                val movies = remoteService.listPopularMovies(
                    apiKey,1
                )
                Log.d("TestNicolas", ": movies size ${movies.results.size} ")
            }
        }
}
