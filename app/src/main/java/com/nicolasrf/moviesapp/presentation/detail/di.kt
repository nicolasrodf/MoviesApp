package com.nicolasrf.moviesapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.nicolasrf.moviesapp.di.MovieId
import com.nicolasrf.moviesapp.navigation.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @MovieId
    fun provideMovieId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle[NavArgs.ItemId.key] ?: -1
    }

}