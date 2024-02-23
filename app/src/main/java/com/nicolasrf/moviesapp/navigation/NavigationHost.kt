package com.nicolasrf.moviesapp.navigation

import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolasrf.moviesapp.ui.authentication.login.LoginScreen
import com.nicolasrf.moviesapp.ui.home.HomeScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    onAuthError: (String) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Home.route)
                }, onAuthError = {
                    onAuthError(it)
                }
            )
        }
        composable(NavigationRoute.Home.route) {
            HomeScreen()
        }
    }
}