package com.nicolasrf.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolasrf.moviesapp.presentation.authentication.login.LoginScreen
import com.nicolasrf.moviesapp.presentation.home.HomeScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    onServerError: (String) -> Unit
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Home.route)
                }, onAuthError = {
                    onServerError(it)
                }
            )
        }
        composable(NavigationRoute.Home.route) {
            HomeScreen(onMovieClick = {

            }, onPagingError = {
                onServerError(it)
            })
        }
    }
}