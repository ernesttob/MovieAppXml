package com.example.netfilxcloneapp.presentation.screens.home

sealed interface HomeScreenEvent {

    data object OnFetchPopularMovies: HomeScreenEvent
}