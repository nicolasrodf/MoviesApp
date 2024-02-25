package com.nicolasrf.moviesapp.presentation.home

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onPagingError: (String) -> Unit,
    onLogout: () -> Unit
) {

    val pagingState = viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    val vmState = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = pagingState.loadState, key2 = vmState.isLogout) {
        if(pagingState.loadState.refresh is LoadState.Error) {
            onPagingError(
                (pagingState.loadState.refresh as LoadState.Error).error.toError().toUiString(context)
            )
        }
        if(pagingState.loadState.append is LoadState.Error) {
            onPagingError(
                (pagingState.loadState.append as LoadState.Error).error.toError().toUiString(context)
            )
        }
        if (vmState.isLogout) {
            onLogout()
        }
    }

    Scaffold(
        topBar = { MainTopAppBar(onLogout = {
            viewModel.onEvent(HomeEvent.Logout)
        }) }
    ) { padding ->

        if (pagingState.loadState.refresh is LoadState.Loading) {
            Loading(modifier = Modifier.padding(padding))
        }

        MoviesGrid(
            state = pagingState,
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
private fun MainTopAppBar(
    onLogout: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { onLogout() }) {
                Icon(Icons.Default.ExitToApp, "exit menu")
            }
        }
    )
}