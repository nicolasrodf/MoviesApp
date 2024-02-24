package com.nicolasrf.moviesapp.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolasrf.moviesapp.R
import com.nicolasrf.moviesapp.core.presentation.ErrorText
import com.nicolasrf.moviesapp.core.presentation.Loading
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.presentation.home.components.MoviesGrid

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { MainTopAppBar() }
    ) { padding ->

        if (state.loading) {
            Loading(modifier = Modifier.padding(padding))
        }

        state.movies?.let {
            MoviesGrid(
                movies = it,
                onMovieClick = onMovieClick,
                modifier = Modifier.padding(padding)
            )
        }

        state.error?.let { error ->
            ErrorText(
                error = error,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainTopAppBar() {
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
}