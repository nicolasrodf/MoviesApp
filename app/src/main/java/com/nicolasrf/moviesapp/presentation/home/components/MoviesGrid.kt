package com.nicolasrf.moviesapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nicolasrf.moviesapp.domain.model.Movie

@Composable
fun MoviesGrid(
    state: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(state.itemCount) { index ->
            state[index]?.let{
                MovieItem(
                    movie = it,
                    onMovieClick = { onMovieClick(it) }
                )
            }
        }
        item {
            if(state.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}