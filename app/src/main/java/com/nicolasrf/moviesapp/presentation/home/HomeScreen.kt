package com.nicolasrf.moviesapp.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nicolasrf.moviesapp.R
import com.nicolasrf.moviesapp.core.presentation.Loading
import com.nicolasrf.moviesapp.data.toError
import com.nicolasrf.moviesapp.domain.model.Error
import com.nicolasrf.moviesapp.domain.model.Movie
import com.nicolasrf.moviesapp.presentation.home.components.MoviesGrid

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit,
    onPagingError: (String) -> Unit
) {

    val state = viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.loadState) {
        if(state.loadState.refresh is LoadState.Error) {
            onPagingError(
                (state.loadState.refresh as LoadState.Error).error.toError().toUiString(context)
            )
        }
        if(state.loadState.append is LoadState.Error) {
            onPagingError(
                (state.loadState.append as LoadState.Error).error.toError().toUiString(context)
            )
        }
    }

    Scaffold(
        topBar = { MainTopAppBar() }
    ) { padding ->

        if (state.loadState.refresh is LoadState.Loading) {
            Loading(modifier = Modifier.padding(padding))
        }

        MoviesGrid(
            state = state,
            onMovieClick = onMovieClick,
            modifier = Modifier.padding(padding)
        )

    }
}

fun Error.toUiString(context: Context) = when (this) {
    Error.Connectivity -> context.getString(R.string.connectivity_error)
    is Error.Server -> context.getString(R.string.server_error) + code
    is Error.Unknown -> context.getString(R.string.unknown_error) + message
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainTopAppBar() {
    TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
}