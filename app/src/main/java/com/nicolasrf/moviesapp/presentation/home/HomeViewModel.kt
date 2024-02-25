package com.nicolasrf.moviesapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nicolasrf.moviesapp.usecases.GetUpcomingMoviesUseCase
import com.nicolasrf.moviesapp.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val moviesPagingFlow = getUpcomingMoviesUseCase().cachedIn(viewModelScope)

    var state by mutableStateOf(HomeState())
        private set

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Logout -> {
                logout()
            }
        }
    }

    fun logout() {
        state = state.copy(
            isLoading = true
        )
        viewModelScope.launch {
            logoutUseCase().collectLatest {
                state = state.copy(
                    isLogout = it,
                    isLoading = false
                )
            }
        }
    }
}
