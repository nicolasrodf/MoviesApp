package com.nicolasrf.moviesapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolasrf.moviesapp.ui.authentication.login.LoginScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Home.route)
                }
            )
        }
        composable(NavigationRoute.Home.route) {
            Text(text = "Home")
        }
    }
}