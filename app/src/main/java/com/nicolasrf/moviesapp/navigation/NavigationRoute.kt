package com.nicolasrf.moviesapp.navigation

sealed class NavigationRoute(val route: String) {
    data object Login : NavigationRoute("login")
    data object Home : NavigationRoute("home")
    data object Detail : NavigationRoute("detail/{${NavArgs.ItemId.key}}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}

enum class NavArgs(val key: String) {
    ItemId("itemId")
}