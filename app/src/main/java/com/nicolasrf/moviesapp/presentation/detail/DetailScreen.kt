package com.nicolasrf.moviesapp.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolasrf.moviesapp.core.presentation.ErrorText
import com.nicolasrf.moviesapp.presentation.detail.components.MovieContent

@Composable
fun DetailScreen(
    onUpNavClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = state.movie?.title ?: "",
                onUpClick = onUpNavClick,
            )
        }
    ) { padding ->
        state.movie?.let {
            MovieContent(
                movie = it,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String,
    onUpClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Main Screen"
                )
            }
        }
    )
}