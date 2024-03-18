package com.example.netfilxcloneapp.presentation.screens.favorite

import com.example.domain.models.movie_list_domain.MovieDomainModel

sealed interface FavoriteAction {

    data class FavoriteMovies(
        val savedMovies:List<MovieDomainModel>
    ): FavoriteAction

}