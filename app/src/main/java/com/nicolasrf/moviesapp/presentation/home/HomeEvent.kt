package com.nicolasrf.moviesapp.presentation.home

sealed interface HomeEvent {
    data object Logout : HomeEvent
}
