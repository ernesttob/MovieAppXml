package com.example.netfilxcloneapp.presentation.screens.home

import com.example.domain.models.MovieDomainModel

sealed interface HomeScreenAction {

    data class FetchPopularMovies(
        val popularMovies: List<MovieDomainModel>
    ): HomeScreenAction

}