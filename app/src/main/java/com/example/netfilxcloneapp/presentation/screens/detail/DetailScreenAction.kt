package com.example.netfilxcloneapp.presentation.screens.detail

import com.example.domain.models.movie_details_domain.MovieDetailModelDomain

interface DetailScreenAction {

    data class FetchDetailMovie(
        val detailMovie: MovieDetailModelDomain
    ): DetailScreenAction
}