package com.nicolasrf.moviesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nicolasrf.moviesapp.navigation.NavigationHost
import com.nicolasrf.moviesapp.navigation.NavigationRoute
import com.nicolasrf.moviesapp.presentation.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        onAuthError = {
                            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {
        return if (viewModel.isLoggedIn) {
            NavigationRoute.Home
        } else {
            NavigationRoute.Login
        }
    }
}