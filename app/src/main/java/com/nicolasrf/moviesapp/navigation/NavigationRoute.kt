package com.nicolasrf.moviesapp.navigation

sealed class NavigationRoute(val route: String) {
    data object Login : NavigationRoute("login")
    data object Home : NavigationRoute("home")
}