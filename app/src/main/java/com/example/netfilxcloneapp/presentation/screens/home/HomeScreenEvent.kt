package com.example.netfilxcloneapp.presentation.screens.home

sealed interface HomeScreenEvent {

    data object OnFetchAllMovies: HomeScreenEvent

    data class OnNavigateToDetails(
        val movieId: Int
    ) : HomeScreenEvent
}