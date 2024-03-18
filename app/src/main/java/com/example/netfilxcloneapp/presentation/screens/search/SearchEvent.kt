package com.example.netfilxcloneapp.presentation.screens.search

sealed interface SearchEvent {

    data class onSearchMovies(val movieTitle: String):SearchEvent

}